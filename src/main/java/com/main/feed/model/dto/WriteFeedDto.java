package com.main.feed.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteFeedDto {
	private String userId;
	private Long userPlaylistId;
	private Long missionId;
	private String content;
}
