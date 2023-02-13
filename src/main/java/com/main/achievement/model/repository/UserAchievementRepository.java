package com.main.achievement.model.repository;

import com.main.achievement.model.entity.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
	List<UserAchievement> findByUser_UserIdOrderByAchievement_AchievementId(String UserId);
}
