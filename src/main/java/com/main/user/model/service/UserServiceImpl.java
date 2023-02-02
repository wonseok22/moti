package com.main.user.model.service;

import com.main.profile.model.entity.Profile;
import com.main.profile.model.entity.ProfileImage;
import com.main.profile.model.repository.ProfileImageRepository;
import com.main.profile.model.repository.ProfileRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	ProfileImageRepository profileImageRepository;
	
	@Override
	public User getUser(String userId) throws SQLException {
		return userRepository.findByUserId(userId);
	}
	
	@Override
	@Transactional
	public User registUser(User user) throws Exception {


//        Duilicate Check
//        User userFindById = userRepository.findByUserId(user.getUserId());
//        User userFindByNickname = userRepository.findByEmail(user.getEmail());
//        User userFindByEmail = userRepository.findByNickname(user.getNickname());
//        if (userFindByEmail != null || userFindByNickname != null || userFindById != null){
//            return null;
//        }'
		
		
		// Create (Positive Long type) UUID
		Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		
		// Profile Image Build
		ProfileImage profileImage = new ProfileImage();
		profileImage.setProfileImageId(uuid);
		profileImageRepository.save(profileImage);
		
		// Profile Build
		Profile profile = new Profile();
		profile.setProfileId(uuid);
		profile.setFollower(0);
		profile.setFollowing(0);
		profile.setProfileImage(profileImage);
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
		user.setType("default");
		
		return userRepository.save(user);
		
	}
	
	@Override
	public User loginUser(User user) throws Exception {
		User findUser = userRepository.findByUserId(user.getUserId());
		if (findUser == null) {
			return null;
		}
		String userPwd = user.getPassword();
		userPwd += findUser.getSalt();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		// 평문+salt 암호화
		md.update(userPwd.getBytes());
		userPwd = String.format("%064x", new BigInteger(1, md.digest()));
		
		if (userPwd.equals(findUser.getPassword())) {
			return findUser;
		} else {
			return null;
		}
	}
	
	@Override
	public User modifyUser(User user) throws Exception {
		User findUser = userRepository.findByUserId(user.getUserId());
		if (findUser == null) {
			return null;
		}
		String userPwd = user.getPassword();
		userPwd += findUser.getSalt();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		// 평문+salt 암호화
		md.update(userPwd.getBytes());
		userPwd = String.format("%064x", new BigInteger(1, md.digest()));
		user.setSalt(findUser.getSalt());
		user.setPassword(userPwd);
		user.setType("default");
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public int deleteUser(String userId) throws Exception {
		User user = userRepository.findByUserId(userId);
		Profile profile = user.getProfile();
		ProfileImage profileImage = profile.getProfileImage();
		profileRepository.delete(profile);
		profileImageRepository.delete(profileImage);
		return userRepository.deleteByUserId(userId);
	}
	
	@Override
	public User checkUser(String type, String value) throws Exception {
		User user = null;
		if ("id".equals(type)) {
			user = userRepository.findByUserId(value);
		} else if ("nickname".equals(type)) {
			user = userRepository.findByNickname(value);
		} else if ("email".equals(type)) {
			user = userRepository.findByEmail(value);
		} else {
			// 타입 잘못된경우
			return null;
		}
		return user;
	}
	
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		User user = userRepository.findByUserId(userId);
		user.setRefreshToken(refreshToken);
		userRepository.save(user);
	}
	
	@Override
	public String getRefreshToken(String userId) throws Exception {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			return null;
		}
		return user.getRefreshToken();
	}
	
	@Override
	public void delRefreshToken(String userId) throws Exception {
		User user = userRepository.findByUserId(userId);
		user.setRefreshToken(null);
		userRepository.save(user);
	}
	
}
