package com.main.achievement.model.dto;

import com.main.achievement.model.entity.UserAchievement;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MainAchievementDto {
	private String userId;
	private Long achievementId;
}

