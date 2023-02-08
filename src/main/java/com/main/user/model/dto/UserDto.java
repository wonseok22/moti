package com.main.user.model.dto;

import com.main.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
	
	private String userId;
	
	private String password;
	
	private String email;
	
	private String nickname;
	
	private String type;
	
	private int temporary;
	
	private LocalDateTime joinDate;
	
	private String achievementImageUrl;
	
	public static UserDto toDto(User user) {
		return new UserDto(
				user.getUserId(),
				user.getPassword(),
				user.getEmail(),
				user.getNickname(),
				user.getType(),
				user.getTemporary(),
				user.getJoinDate(),
				user.getAchievement()==null?null:user.getAchievement().getAchievementImageUrl()
		);
	}
	
	public User toEntity() {
		return new User(userId, password, email, nickname, null, type, null, temporary, joinDate, null, null, null, null, null, null,null);
	}
	
}
