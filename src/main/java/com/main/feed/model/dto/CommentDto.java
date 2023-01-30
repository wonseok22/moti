package com.main.feed.model.dto;

import com.main.feed.model.entity.Comment;
import com.main.user.model.dto.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentDto {

	private Long commentId;
	private UserDto userDto;
	private String content;
	private LocalDateTime createdDate;
	// Comment에서는 매칭된 Feed가 무엇인지 알 필요가 없다.
	
	public static CommentDto toDto (Comment comment) {
		
		return new CommentDto(
				comment.getCommentId(),
				UserDto.toDto(comment.getUser()),
				comment.getContent(),
				comment.getCreatedDate()
		);
	
	}

}
