package com.main.profile.model.service;

import com.main.profile.model.dto.ProfileDto;
import com.main.profile.model.entity.Profile;
import com.main.profile.model.entity.ProfileImage;
import com.main.profile.model.repository.ProfileImageRepository;
import com.main.profile.model.repository.ProfileRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileImageRepository profileImageRepository;

    @Value("${file.path}")
    private String uploadFolder;


    @Override
    public int modifyProfile(ProfileDto profileDto) throws Exception {

        try{
            String userId = profileDto.getUserId();
            User user = userRepository.findByUserId(userId);
            Profile profile = user.getProfile();
            ProfileImage profileImage = profile.getProfileImage();

            // 닉네임 변경한 경우
            if (profileDto.getNickname() != null ){
                user.setNickname(profileDto.getNickname());
            }

            // 프로필 사진 변경한 경우
            if (profileDto.getImage() != null){
                //이미지 저장 로직
                Path ImageFilePath = Paths.get(uploadFolder+ userId);
                try {
                    Files.write(ImageFilePath, profileDto.getImage().getBytes());
                } catch(Exception e) {
                    e.printStackTrace();
                }
                profileImage.setSaveFolder(userId);
            }

            // 한줄소개 변경한 경우
            if (profileDto.getUserDesc() != null){
                profile.setUserDesc(profileDto.getUserDesc());
            }


            userRepository.save(user);
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public ProfileDto getProfile(String userId) throws Exception {
        User user = userRepository.findByUserId(userId);
        if (user != null){
            Profile profile = user.getProfile();
            ProfileDto profileDto = new ProfileDto();
            profileDto.setNickname(user.getNickname());
            profileDto.setFollowing(profile.getFollowing());
            profileDto.setFollwer(profile.getFollower());
            profileDto.setUserDesc(profile.getUserDesc());
            profileDto.setUserId(userId);
            return profileDto;
        } else {
            return null;
        }

    }
}
