package com.main.profile.model.service;

import com.main.profile.model.dto.FollowDto;
import com.main.profile.model.dto.ProfileDto;
import com.main.user.model.entity.User;

import java.util.List;

public interface ProfileService {
    public int modifyProfile(ProfileDto profileDto) throws Exception;
    public ProfileDto getProfile(String userId) throws Exception;

    public List<FollowDto> getFollow(String type, String userId) throws Exception;
    public int doFollow(String type, String userId, String targetId) throws Exception;

    public void deleteProfileImage(String userId) throws Exception;
}



