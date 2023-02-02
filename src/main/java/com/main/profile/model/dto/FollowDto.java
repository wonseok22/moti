package com.main.profile.model.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowDto {
	private String followerNickname;
	private String followingNickname;
	private String followerId;
	private String followingId;
	
}
