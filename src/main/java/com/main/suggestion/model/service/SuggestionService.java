package com.main.suggestion.model.service;

import com.main.suggestion.model.dto.SuggestionDto;
import com.main.suggestion.model.entity.Suggestion;

public interface SuggestionService {
	Suggestion writeSuggestion(SuggestionDto suggestionDto);
}
