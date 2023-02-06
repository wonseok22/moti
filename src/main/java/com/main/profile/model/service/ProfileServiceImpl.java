package com.main.profile.model.service;

import com.main.achievement.model.entity.Achievement;
import com.main.profile.model.dto.FollowDto;
import com.main.profile.model.dto.ProfileDto;
import com.main.profile.model.entity.CurrentStat;
import com.main.profile.model.entity.Follow;
import com.main.profile.model.entity.Profile;
import com.main.profile.model.repository.CurrentStatRepository;
import com.main.profile.model.repository.FollowRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
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
	private FollowRepository followRepository;
	
	@Autowired
	private CurrentStatRepository currentStatRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Override
	public int modifyProfile(ProfileDto profileDto) throws Exception {
		
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
				String ImagePath = s3Upload.uploadFiles(profileDto.getImage(), "profileImages");
				profile.setProfileImageUrl(ImagePath);
			}
			
			// 한줄소개 변경한 경우
			if (profileDto.getUserDesc() != null) {
				profile.setUserDesc(profileDto.getUserDesc());
			}
			
			
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	@Override
	@Transactional
	public void deleteProfileImage(String userId) throws Exception {
		try {
			User user = userRepository.findByUserId(userId);
			Profile profile = user.getProfile();
			s3Upload.fileDelete(profile.getProfileImageUrl().split("com/")[1]);
			profile.setProfileImageUrl(null);
			userRepository.save(user);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public ProfileDto getProfile(String userId) throws Exception {
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
		} else {
			return null;
		}
		
	}
	
	@Override
	public List<FollowDto> getFollow(String type, String userId) throws Exception {
		User user = userRepository.findByUserId(userId);
		List<Follow> follows = null;
		
		// 팔로워 목록 요청인지 팔로잉 목록 요청인지
		if ("Follower".equals(type)) {
			follows = followRepository.findAllByFollowingId(userId);
		} else {
			follows = followRepository.findAllByFollowerId(userId);
		}
		
		// Dto로 전달하기 위해 DtoList 생성
		List<FollowDto> followList = new ArrayList();
		
		//모든 Follower 또는 Following 목록에 대해 Dto 생성
		for (Follow f : follows) {
			followList.add(
					new FollowDto(
							f.getFollowerNickname(),
							f.getFollowingNickname(),
							f.getFollowerId(),
							f.getFollowingId()
					));
		}
		return followList;
	}
	
	@Override
	public int doFollow(String type, String userId, String targetId) throws Exception {
		Follow follow = new Follow();
		User requestuser = userRepository.findByUserId(userId);
		User targetUser = userRepository.findByUserId(targetId);
		
		try {
			// 팔로우 요청인 경우
			if ("follow".equals(type)) {
				follow.setFollowerId(userId);
				follow.setFollowingId(targetId);
				follow.setFollowerNickname(requestuser.getNickname());
				follow.setFollowingNickname(targetUser.getNickname());
				followRepository.save(follow);
				
				// 팔로우 취소 요청인 경우
			} else {
				follow = followRepository.findByFollowerIdAndFollowingId(requestuser.getUserId(), targetUser.getUserId());
				followRepository.delete(follow);
			}
			return 1;
		} catch (Exception e) {
			return -1;
		}
		
	}
	
	@Override
	public boolean checkFollow(String userId, String targetId) {
		return followRepository.findByFollowerIdAndFollowingId(userId, targetId) == null ? false : true;
	}
	
}
