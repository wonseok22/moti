package com.main.feed.model.dto;

import com.main.feed.model.entity.FeedImage;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class FeedImageDto {
	
	private Long feedImageId;
	private FeedDto feed;
	private String feedImageUrl;
	
	public static FeedImageDto toDto (FeedImage file) {
		return new FeedImageDto(
				file.getFeedImageId(),
				FeedDto.toDto(file.getFeed()),
				file.getFeedImageUrl()
		);
	}
	
}
