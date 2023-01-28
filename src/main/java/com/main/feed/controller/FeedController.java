package com.main.feed.controller;

import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.service.FeedService;
import com.main.feed.model.service.FileService;
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
	private FileService fileService;
	
	@Autowired
	private JwtService jwtService;
	
	@ApiOperation(value = "피드 작성", notes = "피드 작성 API", response = Map.class)
	@PostMapping
	public ResponseEntity<?> writeFeed (
			@RequestBody @ApiParam(value = "피드 작성 정보", required = true) WriteFeedDto writeFeedDto) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		try {
			Feed feed = feedService.writeFeed(writeFeedDto);
			if(feed != null) {
				System.out.println("feed.id = " + feed.getFeedId());
				System.out.println("feed.content = " + feed.getContent());
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
	
}
