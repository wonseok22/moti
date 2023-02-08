package com.main.user.model.service;

import com.main.profile.model.entity.Profile;
import com.main.profile.model.repository.ProfileRepository;
import com.main.user.model.dto.SearchUserDto;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Override
	public User getUser(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	@Override
	@Transactional
	public User registerUser (User user) throws Exception {

//        Duilicate Check
//        User userFindById = userRepository.findByUserId(user.getUserId());
//        User userFindByNickname = userRepository.findByEmail(user.getEmail());
//        User userFindByEmail = userRepository.findByNickname(user.getNickname());
//        if (userFindByEmail != null || userFindByNickname != null || userFindById != null){
//            return null;
//        }'
		
		// Profile Build
		Profile profile = new Profile();
		profileRepository.save(profile);
		
		// Password Encoding
		String pw = user.getPassword();
		String hex = "";
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] bytes = new byte[16];
		random.nextBytes(bytes);
		String salt = new String(Base64.getEncoder().encode(bytes));
		String rawAndSalt = pw + salt;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(rawAndSalt.getBytes());
		hex = String.format("%064x", new BigInteger(1, md.digest()));
		
		// User Build
		user.setProfile(profile);
		user.setSalt(salt);
		user.setPassword(hex);
		user.setJoinDate(LocalDateTime.now());
		user.setType("default");
		
		return userRepository.save(user);
		
	}
	
	@Override
	public User loginUser(User user) throws Exception {
		User findUser = userRepository.findByUserId(user.getUserId());
		if (findUser == null) return null;
		
		String salt = findUser.getSalt();
		String userPwd = user.getPassword();
		userPwd += salt;
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		// 평문+salt 암호화
		md.update(userPwd.getBytes());
		userPwd = String.format("%064x", new BigInteger(1, md.digest()));
		
		if (userPwd.equals(findUser.getPassword())) return findUser;
		else return null;
	}
	
	@Override
	public User modifyUser(User user) throws Exception {
		User findUser = userRepository.findByUserId(user.getUserId());
		if (findUser == null) return null;
		
		String salt = findUser.getSalt();
		String userPwd = user.getPassword();
		userPwd += salt;
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		// 평문+salt 암호화
		md.update(userPwd.getBytes());
		userPwd = String.format("%064x", new BigInteger(1, md.digest()));
		findUser.setSalt(salt);
		findUser.setPassword(userPwd);
		findUser.setType("default");
		return userRepository.save(findUser);
	}
	
	@Override
	@Transactional
	public int deleteUser(String userId) {
		User user = userRepository.findByUserId(userId);
		Long profileId = user.getProfile().getProfileId();
		userRepository.deleteByUserId(userId);
		return profileRepository.deleteByProfileId(profileId);
	}
	
	@Override
	public User checkUser(String type, String value) {
		if ("id".equals(type)) return userRepository.findByUserId(value);
		else if ("nickname".equals(type)) return userRepository.findByNickname(value);
		else if ("email".equals(type)) return userRepository.findByEmail(value);
		return null;
	}
	
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) {
		User user = userRepository.findByUserId(userId);
		user.setRefreshToken(refreshToken);
		userRepository.save(user);
	}
	
	@Override
	public String getRefreshToken(String userId) {
		User user = userRepository.findByUserId(userId);
		return user != null ? user.getRefreshToken() : null;
	}
	
	@Override
	public void delRefreshToken(String userId) {
		User user = userRepository.findByUserId(userId);
		user.setRefreshToken(null);
		userRepository.save(user);
	}
	
	@Override
	public Map<String, Object> searchUser (String keyword, int pageNo) {
		Map<String, Object> searchResult = new HashMap<>();
		Slice<User> slice = userRepository.findAllByNicknameLike("%" + keyword + "%", PageRequest.of(pageNo, 20));
		List<SearchUserDto> list = new ArrayList<>();
		slice.forEach(x -> list.add(SearchUserDto.toDto(x)));
		searchResult.put("users", list);
		searchResult.put("isLast", slice.isLast());
		return searchResult;
	}
	
}
