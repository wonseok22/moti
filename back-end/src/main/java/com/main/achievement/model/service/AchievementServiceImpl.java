package com.main.achievement.model.service;

import com.main.achievement.model.dto.AchievementDto;
import com.main.achievement.model.dto.MainAchievementDto;
import com.main.achievement.model.dto.UserAchievementDto;
import com.main.achievement.model.entity.Achievement;
import com.main.achievement.model.repository.AchievementRepository;
import com.main.achievement.model.repository.UserAchievementRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import com.main.util.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {
	
	@Autowired
	private AchievementRepository achievementRepository;
	
	@Autowired
	private UserAchievementRepository userAchievementRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Override
	public Achievement registerAchievement (Achievement achievement, MultipartFile image) {
		try {
			String ImagePath = s3Upload.uploadFiles(image, "achievementImages");
			achievement.setAchievementImageUrl(ImagePath);
			return achievementRepository.save(achievement);
		} catch (Exception e) {
			System.err.println("업적 이미지 업로드 중 에러 발생");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AchievementDto> getAchievements(String userId) {
		List<AchievementDto> achievements = new ArrayList<>();
		List<UserAchievementDto> userAchievements = new ArrayList<>();
		
		achievementRepository.findAll().forEach(x -> achievements.add(AchievementDto.toDto(x)));
		userAchievementRepository.findByUser_UserIdOrderByAchievement_AchievementId(userId).forEach(x -> userAchievements.add(UserAchievementDto.toDto(x)));
		
		for (UserAchievementDto userAchievementDto : userAchievements) {
			achievements.get((int) (userAchievementDto.getAchievementId() - 1)).setAchieved(true);
			achievements.get((int) (userAchievementDto.getAchievementId() - 1)).setAchievedDate(userAchievementDto.getAchievedDate());
		}
		return achievements;
	}
	
	@Override
	public User setMainAchievement(MainAchievementDto mainAchievementDto) {
		User user = userRepository.findByUserId(mainAchievementDto.getUserId());
		if(mainAchievementDto.getAchievementId()==0){
			user.setAchievement(null);
		} else{
			Achievement achievement = achievementRepository.findByAchievementId(mainAchievementDto.getAchievementId());
			user.setAchievement(achievement);
		}
		return userRepository.save(user);
	}
}
