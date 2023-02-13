package com.main.playlist.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaylistMission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistMissionId;
	
	@ManyToOne
	@JoinColumn(name = "missionId")
	private Mission mission;
	
	@ManyToOne
	@JoinColumn(name = "playlistId")
	private Playlist playlist;
}
