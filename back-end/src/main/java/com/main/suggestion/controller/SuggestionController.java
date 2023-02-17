package com.main.suggestion.controller;

import com.main.suggestion.model.dto.SuggestionDto;
import com.main.suggestion.model.entity.Suggestion;
import com.main.suggestion.model.service.SuggestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/suggestion")
@Api(tags = "건의함 관리 API")
public class SuggestionController {
	
	public static final Logger logger = LoggerFactory.getLogger(SuggestionController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private SuggestionService suggestionService;
	
	@ApiOperation(value = "건의 작성", notes = "건의 작성 API", response = Map.class)
	@PostMapping("")
	public ResponseEntity<?> writeSuggestion(
			@RequestBody @ApiParam(value = "건의 내용", required = true) SuggestionDto suggestionDto) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			Suggestion suggestion = suggestionService.writeSuggestion(suggestionDto);
			if (suggestion != null) {
				logger.debug("건의 등록 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("건의 등록 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("건의 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
}
