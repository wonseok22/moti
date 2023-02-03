package com.main.profile.model.service;

import com.main.profile.model.dto.FollowDto;
import com.main.profile.model.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
	int modifyProfile(ProfileDto profileDto) throws Exception;
	
	ProfileDto getProfile(String userId) throws Exception;
	
	List<FollowDto> getFollow(String type, String userId) throws Exception;
	
	int doFollow(String type, String userId, String targetId) throws Exception;
	
	void deleteProfileImage(String userId) throws Exception;
	
	boolean checkFollow(String userId, String targetId);
}



