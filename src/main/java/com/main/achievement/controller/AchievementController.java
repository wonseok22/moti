package com.main.achievement.controller;

import com.main.achievement.model.dto.AchievementDto;
import com.main.achievement.model.entity.Achievement;
import com.main.achievement.model.service.AchievementService;
import com.main.playlist.model.dto.PlaylistDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@Api(tags = {"업적 관리 API"})
@RequestMapping("/achievement")
public class AchievementController {
	public static final Logger logger = LoggerFactory.getLogger(AchievementController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ALREADY_EXIST = "already exists";
	@Autowired
	private AchievementService achievementService;
	
	@ApiOperation(value = "업적 조회", notes = "업적 요청 API", response = Map.class)
	@GetMapping("/{userId}")
	public ResponseEntity<?> getCategory(
			@PathVariable @ApiParam(value = "업적 조회에 필요한 유저아이디", required = true) String userId
	) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			List<AchievementDto> achievements = achievementService.getAchievements(userId);
			
			logger.debug("업적 목록 : {}", achievements);
			resultMap.put("achievements", achievements);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("업적 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "업적 등록", notes = "업적 생성 API", response = Map.class)
	@PostMapping("")
	public ResponseEntity<?> registerAchievement(
			@RequestPart @ApiParam(value = "업적 이름", required = true) String achievementName,
			@RequestPart @ApiParam(value = "업적 설명", required = true) String achievementDesc,
			@RequestPart @ApiParam(value = "이미지 정보", required = true) MultipartFile image) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			Achievement achievement = new Achievement();
			achievement.setAchievementName(achievementName);
			achievement.setAchievementDesc(achievementDesc);
			Achievement result = achievementService.registAchievement(achievement, image);
			
			logger.debug("업적 등록 : {}", result);
			if (result != null)
				resultMap.put("message", SUCCESS);
			else
				resultMap.put("message", FAIL);
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("업적 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
