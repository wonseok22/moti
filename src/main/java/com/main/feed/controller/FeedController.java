package com.main.feed.controller;

import com.main.feed.model.service.FeedService;
import com.main.feed.model.service.FileService;
import com.main.user.model.service.JwtService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
