package com.main.feed.model.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
	
	private Long fileNo;
	private Long feedNo;
	private String saveFolder;
	private String originalFile;
	private String saveFile;
	
}
