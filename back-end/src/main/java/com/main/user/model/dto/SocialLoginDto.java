package com.main.user.model.dto;

import com.main.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class SocialLoginDto {
	private String userId;
	private String email;
	private String nickname;
	private String type;
	private LocalDateTime joinDate;
	private String achievementImageUrl;
	private boolean initial;
	public static SocialLoginDto toDto(User user) {
		return new SocialLoginDto(
				user.getUserId(),
				user.getEmail(),
				user.getNickname(),
				user.getType(),
				user.getJoinDate(),
				user.getAchievement()==null?null:user.getAchievement().getAchievementImageUrl(),
				false
		);
	}
	
}
