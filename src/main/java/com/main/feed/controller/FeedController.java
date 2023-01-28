package com.main.feed.controller;

import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.service.FeedService;
import com.main.user.model.service.JwtService;
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

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/feed")
@Api(tags = "피드 관리 API")
public class FeedController {
	
	public static final Logger logger = LoggerFactory.getLogger(FeedController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ALREADY_EXIST = "already exists";
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private JwtService jwtService;
	
	// 파일 첨부 배제됨
	@ApiOperation(value = "피드 작성", notes = "피드 작성 API", response = Map.class)
	@PostMapping
	public ResponseEntity<?> writeFeed (
			@RequestBody @ApiParam(value = "피드 작성 정보", required = true) WriteFeedDto writeFeedDto) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			Feed feed = feedService.writeFeed(writeFeedDto);
			if(feed != null) {
				logger.debug("피드 등록 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("피드 등록 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("피드 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}
	
	// 파일 첨부 배제됨
	@ApiOperation(value = "피드 1개 조회", notes = "피드 1개 조회 API", response = Map.class)
	@GetMapping("/{feedId}")
	public ResponseEntity<?> viewFeed (
			@PathVariable @ApiParam(value = "조회할 피드 ID", required = true) Long feedId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			FeedDto feed = feedService.viewFeed(feedId);
			logger.debug("피드 조회 결과 : {}", "성공");
			resultMap.put("feed", feed);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("피드 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}
	
	// 파일 첨부 배제됨
	@ApiOperation(value = "피드 수정", notes = "피드 수정 API", response = Map.class)
	@PutMapping("/{feedId}")
	public ResponseEntity<?> modifyFeed (
			@PathVariable @ApiParam(value = "수정할 피드 ID", required = true) Long feedId,
			@RequestBody @ApiParam(value = "수정할 내용", required = true) String content) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			Feed feed = feedService.modifyFeed(feedId, content);
			if(feed != null) {
				logger.debug("피드 수정 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("피드 수정 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("피드 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}
	
	@ApiOperation(value = "피드 삭제", notes = "피드 삭제 API", response = Map.class)
	@DeleteMapping("/{feedId}")
	public ResponseEntity<?> deleteFeed (
			@PathVariable @ApiParam(value = "삭제할 피드 ID", required = true) Long feedId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			int result = feedService.deleteFeed(feedId);
			if (result == 1) {
				logger.debug("피드 삭제 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("피드 삭제 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("피드 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}
	
}
