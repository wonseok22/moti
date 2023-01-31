package com.main.feed.model.dto;

import com.main.feed.model.entity.FeedImage;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class FeedImageDto {
	
	private Long feedImageId;
	private String feedImageUrl;
	
	public static FeedImageDto toDto (FeedImage feedImage) {
		return new FeedImageDto(
				feedImage.getFeedImageId(),
				feedImage.getFeedImageUrl()
		);
	}
	
}
