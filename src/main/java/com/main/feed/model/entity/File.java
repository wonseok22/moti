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
	private Long fileNo;
	
	private Long feedNo;
	
	private String saveFolder;
	
	private String originalFile;
	
	private String saveFile;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feedId")
	private Feed feed;

}
