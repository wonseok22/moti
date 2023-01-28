package com.main.feed.model.dto;

import com.main.feed.model.entity.File;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class FileDto {
	
	private Long fileId;
	private FeedDto feed;
	private String fileImageUrl;
	
	public static FileDto toDto (File file) {
		return new FileDto(
				file.getFileId(),
				FeedDto.toDto(file.getFeed()),
				file.getFileImageUrl()
		);
	}
	
}
