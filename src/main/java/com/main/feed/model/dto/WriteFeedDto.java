package com.main.feed.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class WriteFeedDto {
	private String userId;
	private Long userPlaylistId;
	private Long missionId;
	private String content;
	private List<MultipartFile> images;
}
