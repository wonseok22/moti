package com.main.feed.model.entity;

import com.main.user.model.entity.User;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity(name = "likes")
public class Like {

	@Id
	@GeneratedValue
	private Long likeId;
	
	private Long userId;
	
	private Long feedId;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feedId", insertable = false, updatable = false)
	private Feed feed;

}
