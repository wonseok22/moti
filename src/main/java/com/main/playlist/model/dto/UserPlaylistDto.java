package com.main.playlist.model.dto;

import com.main.playlist.model.entity.UserPlaylist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserPlaylistDto {
	private Long userPlaylistId;
	private String userId;
	private PlaylistDto playlist;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int done;
	private boolean today;
	
	public static UserPlaylistDto toDto(UserPlaylist userPlaylist) {
		return new UserPlaylistDto(
				userPlaylist.getUserPlaylistId(),
				userPlaylist.getUser().getUserId(),
				PlaylistDto.toDto(userPlaylist.getPlaylist()),
				userPlaylist.getStartDate(),
				userPlaylist.getEndDate(),
				userPlaylist.getDone(),
				userPlaylist.getToday() == 0 ? false : true
		);
	}
}
