package com.main.profile.model.dto;

import com.main.profile.model.entity.Profile;
import com.main.profile.model.entity.ProfileImage;
import com.main.user.model.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileDto {

    private String userId;
    private String userDesc;
    private MultipartFile image;
    private String nickname;
    private int follwer;
    private int following;

    private String profileImageUrl;

}
