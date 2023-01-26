package com.main.feed.model.entity;

import com.main.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long commentId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "feed_no")
	private Long feedNo;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	// User 쪽에서 일대다 맵핑 필요
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feedNo")
	private Feed feed;

}
