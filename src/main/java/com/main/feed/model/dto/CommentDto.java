package com.main.feed.model.dto;

import com.main.feed.model.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Long commentId;
	private String userId;
	private Long feedNo;
	private String content;
	private LocalDateTime createdDate;
	
	public CommentDto(Comment comment) {
		commentId = comment.getCommentId();
		userId = comment.getUserId();
		feedNo = comment.getFeedNo();
		content = comment.getContent();
		createdDate = comment.getCreatedDate();
	}

}
