package com.main.feed.model.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class File {

	@Id
	@GeneratedValue
	@Column(name = "file_no")
	private Long fileNo;
	
	@Column(name = "feed_no")
	private Long feedNo;
	
	@Column(name = "save_folder")
	private String saveFolder;
	
	@Column(name = "original_file")
	private String originalFile;
	
	@Column(name = "save_file")
	private String saveFile;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feedId")
	private Feed feed;

}
