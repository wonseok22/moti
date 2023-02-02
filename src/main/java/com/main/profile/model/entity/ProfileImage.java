package com.main.profile.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity(name = "profileImage")
public class ProfileImage {
	@Id
	private Long profileImageId;
	
	private String profileImageUrl;
	
}
