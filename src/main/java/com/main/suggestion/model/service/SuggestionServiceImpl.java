package com.main.suggestion.model.service;

import com.main.suggestion.model.dto.SuggestionDto;
import com.main.suggestion.model.entity.Suggestion;
import com.main.suggestion.model.repository.SuggestionRepository;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	
	private SuggestionRepository suggestionRepository;
	
	@Override
	public Suggestion writeSuggestion (SuggestionDto suggestionDto) {
		Suggestion suggestion = new Suggestion();
		suggestion.setCategoryName(suggestionDto.getCategoryName());
		suggestion.setPlaylistName(suggestionDto.getPlaylistName());
		suggestion.setMission1Name(suggestionDto.getMission1Name());
		suggestion.setMission2Name(suggestionDto.getMission2Name());
		suggestion.setMission3Name(suggestionDto.getMission3Name());
		suggestion.setMission4Name(suggestionDto.getMission4Name());
		suggestion.setMission5Name(suggestionDto.getMission5Name());
		suggestion.setSuggestionContent(suggestionDto.getSuggestionContent());
		
		return suggestionRepository.save(suggestion);
	}
	
}
