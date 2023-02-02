package com.main.profile.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile {
	@Id
	private Long profileId;
	private String userDesc;
	private int follower;
	private int following;
	
	@OneToOne
	@JoinColumn(name = "profileImageId")
	private ProfileImage profileImage;
	
	
}
