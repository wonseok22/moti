package com.main.feed.model.entity;

import com.main.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Feed {

	@Id
	@GeneratedValue
	@Column(name = "feed_no")
	private Long feedNo;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "playlist_id")
	private Long playlistId;
	
	@Column(name = "mission_name")
	private String missionName;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "category_no")
	private Long categoryNo;
	
	// User 쪽에서 일대다 맵핑 필요
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "feed")
	List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	List<File> files = new ArrayList<>();

}