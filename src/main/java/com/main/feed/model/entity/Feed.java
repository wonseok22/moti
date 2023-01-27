package com.main.feed.model.entity;

import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.Playlist;
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
	private Long feedId;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
	
	// 연결 필요
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "playlistId", insertable = false, updatable = false)
	private Playlist playlist;
	
	// 연결 필요
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "missionId", insertable = false, updatable = false)
	private Mission mission;
	
	private String content;
	
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "feed")
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	private List<File> files = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	private List<Like> likes = new ArrayList<>();
	
}
