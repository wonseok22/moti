package com.main.profile.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
	
	@Id
	private Long followId;
	private String followerId;
	private String followingId;
	private String followerNickname;
	private String followingNickname;
}
