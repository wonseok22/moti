package com.main.profile.model.service;

import com.main.profile.model.dto.ProfileDto;
import com.main.profile.model.entity.Profile;

public interface ProfileService {
    public int modifyProfile(ProfileDto profileDto) throws Exception;
    public ProfileDto getProfile(String userId) throws Exception;
}
