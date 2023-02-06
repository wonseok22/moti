package com.main.profile.model.service;

import com.main.profile.model.dto.FollowDto;
import com.main.profile.model.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
	int modifyProfile(ProfileDto profileDto);
	
	ProfileDto getProfile(String userId);
	
	List<FollowDto> getFollow(String type, String userId);
	
	int doFollow(String type, String userId, String targetId);
	
	void deleteProfileImage(String userId);
	
	boolean checkFollow(String userId, String targetId);
}



