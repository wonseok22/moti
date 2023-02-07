package com.main.profile.model.service;

import com.main.profile.model.dto.GetFollowDto;
import com.main.profile.model.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
	int modifyProfile(ProfileDto profileDto);
	
	ProfileDto getProfile(String userId);
	
	List<GetFollowDto> getFollow(String type, String userId);
	
	int doFollow(String type, String userId, String targetId);
	
	void deleteProfileImage(String userId);
	
	boolean checkFollow(String userId, String targetId);
}



