package com.main.flower.controller;

import com.main.flower.model.entity.Flower;
import com.main.flower.model.service.FlowerService;
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
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@Api(tags = {"꽃 관리 API"})
@RequestMapping("/flower")
public class FlowerController {
	
	@Autowired
	private FlowerService flowerService;
	
	public static final Logger logger = LoggerFactory.getLogger(FlowerController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ALREADY_EXIST = "already exists";

	@ApiOperation(value = "꽃 등록", notes = "꽃 등록 API", response = Map.class)
	@PostMapping("")
	public ResponseEntity<?> registFlower(
			@RequestPart @ApiParam(value = "이미지 정보", required = true) MultipartFile image) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			Flower flower = flowerService.registerFlower(image);
			
			if(flower!=null) {
				logger.debug("꽃 정보 : {}", flower);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
			else{
				resultMap.put("message", FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("꽃 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
