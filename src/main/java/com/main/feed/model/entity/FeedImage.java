package com.main.feed.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedImageId;
	
	@ManyToOne
	@JoinColumn(name = "feedId")
	private Feed feed;
	
	private String feedImageUrl;
	
}
