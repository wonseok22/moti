package com.main.user.model.dto;

import com.main.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SearchUserDto {
	private String userId;
	private String nickname;
	private String profileImageUrl;
	private String achievementImageUrl;

	public static SearchUserDto toDto(User user) {
		return new SearchUserDto(
				user.getUserId(),
				user.getNickname(),
				user.getProfile().getProfileImageUrl(),
				user.getAchievement().getAchievementImageUrl()
		);
	}
}
