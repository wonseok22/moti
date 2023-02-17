package com.main.feed.model.dto;

import com.main.feed.model.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class LikeDto {
	
	private Long likeId;
	private Long feedId;
	private String userId;
	
	public static LikeDto toDto(Like like) {
		
		return new LikeDto(
				like.getLikeId(),
				like.getFeed().getFeedId(),
				like.getUser().getUserId()
		);
		
	}
	
}
