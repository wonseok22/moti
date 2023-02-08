package com.main.achievement.model.dto;

import com.main.achievement.model.entity.Achievement;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AchievementDto {
	private Long achievementId;
	private String achievementName;
	
	private String achievementDesc;
	
	private String achievementImageUrl;
	
	private boolean achieved = false;
	
	private LocalDateTime achievedDate;
	
	public static AchievementDto toDto(Achievement achievement) {
		AchievementDto achievementDto = new AchievementDto();
		achievementDto.setAchievementId(achievement.getAchievementId());
		achievementDto.setAchievementName(achievement.getAchievementName());
		achievementDto.setAchievementDesc(achievement.getAchievementDesc());
		achievementDto.setAchievementImageUrl(achievement.getAchievementImageUrl());
		
		return achievementDto;
		
	}
}

