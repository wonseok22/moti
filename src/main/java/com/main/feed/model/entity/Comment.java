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
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feedId")
	private Feed feed;
	
	private String content;
	
	private LocalDateTime createdDate;
	
}
