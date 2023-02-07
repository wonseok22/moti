package com.main.profile.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GetFollowDto {
	private String userId;
	private String nickname;
	private String profileImageUrl;
}
