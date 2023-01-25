package com.main.feed.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {
	
	private Long feedNo;
	private String userId;
	private int playlistId;
	private String playlistName;
	private String missionName;
	private String content;
	private LocalDateTime createdDate;

}
