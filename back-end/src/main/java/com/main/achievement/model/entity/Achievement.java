package com.main.achievement.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Achievement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long achievementId;
	
	@Column(nullable = false, unique = true)
	private String achievementName;
	
	private String achievementDesc;
	
	private String achievementImageUrl;
	
	@OneToMany(mappedBy = "achievement")
	private List<UserAchievement> userAchievements = new ArrayList<>();
}
