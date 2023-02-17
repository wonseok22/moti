package com.main.suggestion.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SuggestionDto {
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
