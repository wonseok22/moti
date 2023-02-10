package com.main.profile.model.service;

import com.main.achievement.model.entity.Achievement;
import com.main.profile.model.dto.GetFollowDto;
import com.main.profile.model.dto.ProfileDto;
import com.main.profile.model.entity.CurrentStat;
import com.main.profile.model.entity.Follow;
import com.main.profile.model.entity.Profile;
import com.main.profile.model.repository.CurrentStatRepository;
import com.main.profile.model.repository.FollowRepository;
import com.main.profile.model.repository.ProfileRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import com.main.util.ImageProcess;
import com.main.util.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private CurrentStatRepository currentStatRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Autowired
	private ImageProcess imageProcess;
	
	@Override
	public int modifyProfile(ProfileDto profileDto) {
		
		try {
			String userId = profileDto.getUserId();
			User user = userRepository.findByUserId(userId);
			Profile profile = user.getProfile();
			
			// 닉네임 변경한 경우
			if (profileDto.getNickname() != null) {
				user.setNickname(profileDto.getNickname());
			}
			
			// 프로필 사진 변경한 경우
			if (profileDto.getImage() != null) {
				//이미지 저장 로직
				if (profile.getProfileImageUrl() != null) {
					s3Upload.fileDelete(profile.getProfileImageUrl().split("com/")[1]);
				}
				String ImagePath = s3Upload.uploadFiles(imageProcess.resizeImage(profileDto.getImage(), 180), "profileImages");
				profile.setProfileImageUrl(ImagePath);
			}
			
			// 한줄소개 변경한 경우
			if (profileDto.getUserDesc() != null) {
				profile.setUserDesc(profileDto.getUserDesc());
			}
			
			userRepository.save(user);
			profileRepository.save(profile);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	@Override
	@Transactional
	public void deleteProfileImage(String userId) {
		try {
			User user = userRepository.findByUserId(userId);
			Profile profile = user.getProfile();
			s3Upload.fileDelete(profile.getProfileImageUrl().split("com/")[1]);
			profile.setProfileImageUrl(null);
			profileRepository.save(profile);
		} catch (Exception e) {
			System.err.println("프로필 이미지 삭제 중 에러 발생");
			e.printStackTrace();
		}
	}
	
	@Override
	public ProfileDto getProfile(String userId) {
		User user = userRepository.findByUserId(userId);
		// 존재하는 유저인 경우
		if (user != null) {
			// 프로필을 받아와서 return
			Profile profile = user.getProfile();
			
			//반환용
			ProfileDto profileDto = new ProfileDto();
			
			// 유저아이디
			profileDto.setUserId(userId);
			
			// 닉네임
			profileDto.setNickname(user.getNickname());
			
			// 프로필 자기소개
			profileDto.setUserDesc(profile.getUserDesc());
			
			// 프로필 이미지
			String profileImageUrl = profile.getProfileImageUrl();
			if (profileImageUrl != null)
				profileDto.setProfileImageUrl(profile.getProfileImageUrl());
			
			// 대표 업적
			Achievement achievement = user.getAchievement();
			if (achievement != null)
				profileDto.setAchievementImageUrl(achievement.getAchievementImageUrl());
			
			//통계자료
			CurrentStat currentStat = currentStatRepository.findByUserId(userId);
			
			// 플레이리스트 성공 개수
			int playlistCompleteCnt = currentStat.getP1_cnt()
					+ currentStat.getP2_cnt()
					+ currentStat.getP3_cnt()
					+ currentStat.getP4_cnt()
					+ currentStat.getP5_cnt()
					+ currentStat.getP6_cnt();
			
			profileDto.setPlaylistCompleteCnt(playlistCompleteCnt);
			
			// 팔로우
			profileDto.setFollower(currentStat.getFollowerCnt());
			
			//팔로잉
			profileDto.setFollowing(currentStat.getFollowingCnt());
			
			return profileDto;
		}
		
		return null;
	}
	
	@Override
	public List<GetFollowDto> getFollow (String type, String userId, String targetId) {
		List<Follow> follows;
		
		// Dto로 전달하기 위해 DtoList 생성
		List<GetFollowDto> followList = new ArrayList<>();
		
		// 팔로워 목록 요청인지 팔로잉 목록 요청인지
		if ("follower".equals(type)) {
			follows = followRepository.findAllByFollowingId(targetId);
			
			follows.forEach(x -> {
				User user = userRepository.findByUserId(x.getFollowerId());
		
				followList.add(
						new GetFollowDto(
								x.getFollowerId(),
								user.getNickname(),
								user.getProfile().getProfileImageUrl(),
								followRepository.findByFollowerIdAndFollowingId(userId, user.getUserId()) != null
						)
				);
			});
		} else {
			follows = followRepository.findAllByFollowerId(targetId);
			
			follows.forEach(x -> {
				User user = userRepository.findByUserId(x.getFollowingId());
				
				followList.add(
						new GetFollowDto(
								x.getFollowingId(),
								user.getNickname(),
								user.getProfile().getProfileImageUrl(),
								userId == targetId ? true : followRepository.findByFollowerIdAndFollowingId(userId, user.getUserId()) != null
						)
				);
			});
		}
		
		return followList;
	}
	
	@Override
	public int doFollow(String type, String userId, String targetId) {
		Follow follow = new Follow();
		User requestUser = userRepository.findByUserId(userId);
		User targetUser = userRepository.findByUserId(targetId);
		
		try {
			// 팔로우 요청인 경우
			if ("follow".equals(type)) {
				follow.setFollowerId(userId);
				follow.setFollowingId(targetId);
				follow.setFollowerNickname(requestUser.getNickname());
				follow.setFollowingNickname(targetUser.getNickname());
				followRepository.save(follow);
				
				// 팔로우 취소 요청인 경우
			} else {
				follow = followRepository.findByFollowerIdAndFollowingId(requestUser.getUserId(), targetUser.getUserId());
				followRepository.delete(follow);
			}
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public boolean checkFollow(String userId, String targetId) {
		return followRepository.findByFollowerIdAndFollowingId(userId, targetId) != null;
	}
	
}
