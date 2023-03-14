package com.main.profile.model.dto;

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
	private int follower;
	private int following;
	private String profileImageUrl;
	private String achievementImageUrl;
	private int playlistCompleteCnt;
}
