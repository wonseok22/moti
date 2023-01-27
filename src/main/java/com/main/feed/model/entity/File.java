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
	private Long fileId;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feedId", insertable = false, updatable = false)
	private Feed feed;
	
	private String fileImageUrl;
	
}
