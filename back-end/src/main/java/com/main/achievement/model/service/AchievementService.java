package com.main.achievement.model.service;

import com.main.achievement.model.dto.AchievementDto;
import com.main.achievement.model.dto.MainAchievementDto;
import com.main.achievement.model.entity.Achievement;
import com.main.user.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

public interface AchievementService {
	Achievement registerAchievement (Achievement achievement, MultipartFile image) throws SQLException;
	
	List<AchievementDto> getAchievements(String userId) throws SQLException;
	
	User setMainAchievement(MainAchievementDto mainAchievementDto) throws SQLException;
}

