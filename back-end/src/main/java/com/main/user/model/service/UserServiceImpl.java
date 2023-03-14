package com.main.user.model.service;

import com.main.feed.model.repository.CommentRepository;
import com.main.feed.model.repository.FeedImageRepository;
import com.main.feed.model.repository.FeedRepository;
import com.main.feed.model.repository.LikeRepository;
import com.main.playlist.model.repository.UserPlaylistRepository;
import com.main.profile.model.entity.Profile;
import com.main.profile.model.repository.FollowRepository;
import com.main.profile.model.repository.ProfileRepository;
import com.main.user.model.dto.SearchUserDto;
import com.main.user.model.dto.SocialLoginDto;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import com.main.util.S3Upload;
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
	private UserRepository userRepository;
	
	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private FeedImageRepository feedImageRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private UserPlaylistRepository userPlaylistRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Override
	public User getUser(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	@Override
	@Transactional
	public User registerUser (User user) throws Exception {
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
		Profile profile = user.getProfile();
		Long profileId = profile.getProfileId();
		commentRepository.deleteAllByUser_UserId(userId);
		likeRepository.deleteAllByUser_UserId(userId);
		feedRepository.findAllByUser_UserId(userId).forEach(x -> {
			feedImageRepository.findAllByFeed_FeedId(x.getFeedId()).forEach(y -> {
				try {
					s3Upload.fileDelete(y.getFeedImageUrl().split(".com/")[1]);
				} catch (Exception e) {
					System.err.println("피드 사진 삭제 중 에러 발생");
					e.printStackTrace();
				}
			});
		}); // 해당 유저가 쓴 피드의 이미지를 서버에서 모두 삭제
		
		try {
			s3Upload.fileDelete(profile.getProfileImageUrl().split(".com/")[1]);
		} catch (Exception e) {
			System.err.println("프로필 사진 삭제 중 에러 발생");
			e.printStackTrace();
		} // 해당 유저의 프로필 이미지를 서버에서 삭제
		
		userPlaylistRepository.deleteAllByUser_UserId(userId);
		followRepository.deleteAllByFollowingId(userId);
		followRepository.deleteAllByFollowerId(userId);
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
	
	@Override
	public SocialLoginDto socialLogin(User userDto, String refreshToken) {
		User user = userRepository.findByUserId(userDto.getUserId());
		SocialLoginDto socialLoginDto;
		// 해당 ID로 가입된 유저가 없으면 가입 시도
		if(user == null) {
			user = userRepository.findByEmail(userDto.getEmail());
			
			// 해당 Email로 가입된 유저가 있으면 종료
			if(user != null) return null;
			
			user = new User();
			
			// Profile Build
			Profile profile = new Profile();
			profileRepository.save(profile);
			
			// User Build
			user.setUserId(userDto.getUserId());
			user.setNickname(userDto.getUserId());
			user.setEmail(userDto.getEmail());
			user.setProfile(profile);
			user.setJoinDate(LocalDateTime.now());
			user.setType(userDto.getType());
			user.setRefreshToken(refreshToken);
			socialLoginDto = SocialLoginDto.toDto(user);
			socialLoginDto.setInitial(true);
		}
		else
			socialLoginDto = SocialLoginDto.toDto(user);
		
		user.setRefreshToken(refreshToken);
		userRepository.save(user);
		return socialLoginDto;
	}
	
}
