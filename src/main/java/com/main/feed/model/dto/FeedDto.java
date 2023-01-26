package com.main.feed.model.dto;

import com.main.feed.model.entity.Feed;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {
	
	private Long feedNo;
	private String userId;
	private Long playlistId;
	private String missionName;
	private String content;
	private LocalDateTime createdDate;
	private Long categoryNo;
	
	public FeedDto(Feed feed) {
		feedNo = feed.getFeedNo();
		userId = feed.getUserId();
		playlistId = feed.getPlaylistId();
		missionName = feed.getMissionName();
		content = feed.getContent();
		createdDate = feed.getCreatedDate();
		categoryNo = feed.getCategoryNo();
	}
	
}
