package com.main.playlist.model.dto;

import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.Playlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class PlaylistDto {
	private Long playlistId;
	
	private String playlistName;
	
	private String playlistDesc;
	private String categoryName;
	private List<Mission> missions;
	
	private int total;
	
	private int current;
	
	private FlowerDto flower;
	
	public static PlaylistDto toDto(Playlist playlist) {
		List<Mission> mission = new ArrayList<>();
		playlist.getPlaylistMissions().forEach(x -> mission.add(x.getMission()));
		AtomicInteger totalCnt = new AtomicInteger();
		AtomicInteger currentCnt = new AtomicInteger();
		
		playlist.getUserPlaylists().forEach(p -> {
					if (p.getEndDate().isAfter(LocalDateTime.now()))
						currentCnt.getAndIncrement();
					totalCnt.getAndIncrement();
				}
		);
		return new PlaylistDto(
				playlist.getPlaylistId(),
				playlist.getPlaylistName(),
				playlist.getPlaylistDesc(),
				playlist.getCategory().getCategoryName(),
				mission,
				totalCnt.get(),
				currentCnt.get(),
				FlowerDto.toDto(playlist.getFlower())
		);
	}
}

