package com.main.profile.model.service;

import com.main.profile.model.dto.FollowDto;
import com.main.profile.model.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    public int modifyProfile(ProfileDto profileDto) throws Exception;
    public ProfileDto getProfile(String userId) throws Exception;

    public List<FollowDto> getFollow(String type, String userId) throws Exception;
    public int doFollow(String type, String userId, String targetId) throws Exception;
}



