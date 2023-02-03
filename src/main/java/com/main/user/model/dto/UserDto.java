package com.main.user.model.dto;

import com.main.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
	
	private String joinDate;
	
	public static UserDto toDto(User user) {
		return new UserDto(
				user.getUserId(),
				user.getPassword(),
				user.getEmail(),
				user.getNickname(),
				user.getType(),
				user.getTemporary(),
				user.getJoinDate()
		);
	}
	
	public User toEntity() {
		return new User(userId, password, email, nickname, null, type, null, temporary, joinDate, null, null, null, null, null, null);
	}
	
}
