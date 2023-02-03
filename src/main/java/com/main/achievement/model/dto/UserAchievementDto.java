package com.main.achievement.model.dto;

import com.main.achievement.model.entity.Achievement;
import com.main.achievement.model.entity.UserAchievement;
import com.main.user.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAchievementDto {
	
	private Long achievementId;
	
	private LocalDateTime achievedDate;
	private int current;
	
	public static UserAchievementDto toDto(UserAchievement userAchievement) {
		UserAchievementDto userAchievementDto = new UserAchievementDto();
		
		userAchievementDto.setAchievementId(userAchievement.getAchievement().getAchievementId());
		userAchievementDto.setAchievedDate(userAchievement.getAchievedDate());
		
		return userAchievementDto;
		
	}
}

