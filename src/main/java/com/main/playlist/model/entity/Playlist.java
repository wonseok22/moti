package com.main.playlist.model.entity;

import com.main.category.model.entity.Category;
import com.main.flower.model.entity.Flower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistId;
	
	@Column(unique = true, nullable = false)
	private String playlistName;
	
	private String playlistDesc;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@OneToMany(mappedBy = "playlist")
	private List<UserPlaylist> userPlaylists = new ArrayList<>();
	
	@OneToMany(mappedBy = "playlist")
	private List<PlaylistMission> playlistMissions = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "flowerId")
	private Flower flower;
}
