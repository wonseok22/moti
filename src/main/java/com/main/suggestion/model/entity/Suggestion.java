package com.main.suggestion.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long suggestionId;
	
	private String categoryName;
	
	private String playlistName;
	
	private String mission1Name;
	
	private String mission2Name;
	
	private String mission3Name;
	
	private String mission4Name;
	
	private String mission5Name;
	
	private String suggestionContent;

}
