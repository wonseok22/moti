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
	private Long commentId;
	
	private String userId;
	
	private Long feedNo;
	
	private String content;
	
	private LocalDateTime createdDate;
	
	// User 쪽에서 일대다 맵핑 필요
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feedNo", insertable = false, updatable = false)
	private Feed feed;

}
