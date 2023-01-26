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
	private Long feedNo;
	
	private String userId;
	
	private Long playlistId;
	
	private String missionName;
	
	private String content;
	
	private LocalDateTime createdDate;
	
	private Long categoryNo;
	
	// User 쪽에서 일대다 맵핑 필요
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
	
	@OneToMany(mappedBy = "feed")
	List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	List<File> files = new ArrayList<>();

}
