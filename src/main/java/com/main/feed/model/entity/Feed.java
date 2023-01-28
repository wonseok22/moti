package com.main.feed.model.entity;

import com.main.category.model.entity.Category;
import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.UserPlaylist;
import com.main.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Feed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "userPlaylistId")
	private UserPlaylist userPlaylist;
	
	@ManyToOne
	@JoinColumn(name = "missionId")
	private Mission mission;
	
	private String content;
	
	private LocalDateTime createdDate;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@OneToMany(mappedBy = "feed")
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	private List<File> files = new ArrayList<>();
	
	@OneToMany(mappedBy = "feed")
	private List<Like> likes = new ArrayList<>();
	
}
