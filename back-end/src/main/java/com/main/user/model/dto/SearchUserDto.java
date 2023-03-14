package com.main.user.model.dto;

import com.main.achievement.model.entity.Achievement;
import com.main.profile.model.entity.Profile;
import com.main.user.model.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserDto {
	private String userId;
	private String nickname;
	private String profileImageUrl;
	private String achievementImageUrl;

	public static SearchUserDto toDto(User user) {
		Profile profile = user.getProfile();
		Achievement achievement = user.getAchievement();
		return new SearchUserDto(
				user.getUserId(),
				user.getNickname(),
				profile == null ? null : profile.getProfileImageUrl(),
				achievement == null ? null : achievement.getAchievementImageUrl()
		);
	}
}
