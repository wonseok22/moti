package com.main.feed.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Feed {

	@Id
	@GeneratedValue
	@Column(name = "feed_no")
	private Long feedNo;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "playlist_id")
	private int playlistId;
	
	@Column(name = "playlist_name")
	private String playlistName;
	
	@Column(name = "mission_name")
	private String missionName;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;

}
