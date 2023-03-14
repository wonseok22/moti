package com.main.feed.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteCommentDto {
	private String userId;
	private Long feedId;
	private String content;
}
