package com.main.playlist.model.entity;

import com.main.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPlaylist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userPlaylistId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "playlistId")
	private Playlist playlist;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private int done;
	
	private int today;
	
}
