package com.main.feed.model.dto;

import com.main.feed.model.entity.Like;
import com.main.user.model.dto.UserDto;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class LikeDto {

	private Long likeId;
	private UserDto user;
	
	public static LikeDto toDto (Like like) {
	
		return new LikeDto(
				like.getLikeId(),
				UserDto.toDto(like.getUser())
		);
	
	}
	
}
