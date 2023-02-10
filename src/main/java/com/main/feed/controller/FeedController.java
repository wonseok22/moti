package com.main.feed.controller;

import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.dto.WriteCommentDto;
import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Comment;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.entity.Like;
import com.main.feed.model.service.FeedService;
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

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
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
	
	// ------------------------------------------------------------------
	// APIs for FEED
	// ------------------------------------------------------------------
	
	@Transactional
	@ApiOperation(value = "피드 작성", notes = "피드 작성 API", response = Map.class)
	@PostMapping("")
	public ResponseEntity<?> writeFeed(
			@RequestPart @ApiParam(value = "피드 작성 정보", required = true) WriteFeedDto writeFeedDto,
			@RequestPart @ApiParam(value = "이미지 정보") List<MultipartFile> images) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			Feed feed = feedService.writeFeed(writeFeedDto, images);
			if (feed != null) {
				logger.debug("피드 등록 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				resultMap.put("feedId", feed.getFeedId());
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
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	@ApiOperation(value = "피드 1개 조회", notes = "피드 1개 조회 API", response = Map.class)
	@GetMapping("/{feedId}/{userId}")
	public ResponseEntity<?> viewFeed(
			@PathVariable @ApiParam(value = "조회할 피드 ID", required = true) Long feedId,
			@PathVariable @ApiParam(value = "조회하는 유저 ID", required = true) String userId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			FeedDto feed = feedService.viewFeed(feedId, userId);
			
			if (feed == null) {
				logger.debug("피드 조회 결과 : {}", "피드 존재하지 않음");
				resultMap.put("message", "존재하지 않는 피드");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<>(resultMap, status);
			}
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
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	@ApiOperation(value = "피드 수정", notes = "피드 수정 API", response = Map.class)
	@PutMapping("/{feedId}")
	public ResponseEntity<?> modifyFeed(
			@PathVariable @ApiParam(value = "수정할 피드 ID", required = true) Long feedId,
			@RequestPart @ApiParam(value = "수정할 내용", required = true) String content,
			@RequestPart @ApiParam(value = "이미지 정보") List<MultipartFile> images) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			Feed feed = feedService.modifyFeed(feedId, content, images);
			if (feed != null) {
				logger.debug("피드 수정 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				resultMap.put("feedId", feedId);
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
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	@ApiOperation(value = "피드 삭제", notes = "피드 삭제 API", response = Map.class)
	@DeleteMapping("/{feedId}")
	public ResponseEntity<?> deleteFeed(
			@PathVariable @ApiParam(value = "삭제할 피드 ID", required = true) Long feedId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			int result = feedService.deleteFeed(feedId);
			if (result == 1) {
				logger.debug("피드 삭제 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				resultMap.put("feedId", feedId);
				status = HttpStatus.OK;
			} else if (result == -1) {
				logger.debug("피드 삭제 결과 : {}", "피드 존재하지 않음");
				resultMap.put("message", "존재하지 않는 피드");
				status = HttpStatus.ACCEPTED;
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
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	// ------------------------------------------------------------------
	// APIs for COMMENT
	// ------------------------------------------------------------------
	
	@ApiOperation(value = "댓글 작성", notes = "댓글 작성 API", response = Map.class)
	@PostMapping("/comment")
	public ResponseEntity<?> writeComment(
			@RequestBody @ApiParam(value = "댓글 내용", required = true) WriteCommentDto writeCommentDto) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			Comment comment = feedService.writeComment(writeCommentDto);
			if (comment != null) {
				logger.debug("댓글 등록 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("댓글 등록 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("댓글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	@ApiOperation(value = "댓글 삭제", notes = "댓글 삭제 API", response = Map.class)
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> deleteComment(
			@PathVariable @ApiParam(value = "삭제할 댓글 ID", required = true) Long commentId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			int result = feedService.deleteComment(commentId);
			if (result == 1) {
				logger.debug("댓글 삭제 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("댓글 삭제 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("댓글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	// ------------------------------------------------------------------
	// APIs for LIKE
	// ------------------------------------------------------------------
	
	@ApiOperation(value = "좋아요 추가", notes = "좋아요 추가 API", response = Map.class)
	@PostMapping("/like/{userId}/{feedId}")
	public ResponseEntity<?> addLike(
			@PathVariable @ApiParam(value = "좋아요 누른 유저 ID", required = true) String userId,
			@PathVariable @ApiParam(value = "좋아요 누른 피드 ID", required = true) Long feedId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			Like like = feedService.addLike(userId, feedId);
			if (like != null) {
				logger.debug("좋아요 추가 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("좋아요 추가 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("좋아요 추가 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	@ApiOperation(value = "좋아요 취소", notes = "좋아요 취소 API", response = Map.class)
	@DeleteMapping("/like/{userId}/{feedId}")
	public ResponseEntity<?> deleteLike(
			@PathVariable @ApiParam(value = "좋아요 누른 유저 ID", required = true) String userId,
			@PathVariable @ApiParam(value = "좋아요 누른 피드 ID", required = true) Long feedId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			int result = feedService.deleteLike(userId, feedId);
			if (result == 1) {
				logger.debug("좋아요 취소 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("좋아요 취소 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("좋아요 취소 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	// ------------------------------------------------------------------
	// API for SEARCH
	// ------------------------------------------------------------------
	
	@ApiOperation(value = "피드 검색", notes = "검색어 기반 피드 검색 API", response = Map.class)
	@GetMapping("/search/{userId}/{keyword}/{kind}/{minFeedId}")
	public ResponseEntity<?> searchFeed(
			@PathVariable @ApiParam(value = "검색하는 유저 ID", required = true) String userId,
			@PathVariable @ApiParam(value = "검색어", required = true) String keyword,
			@PathVariable @ApiParam(value = "검색 종류(default, playlist, content, userId)", required = true) String kind,
			@PathVariable @ApiParam(value = "이전 페이지에서 가장 마지막에 불러온 피드 ID", required = true) Long minFeedId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			Map<String, Object> searchResult = feedService.searchFeed(userId, keyword, kind, minFeedId);
			if (searchResult.get("feeds") == null) {
				logger.debug("피드 검색 결과 : {}", "피드 존재하지 않음");
				resultMap.put("feeds", null);
				resultMap.put("message", "존재하지 않는 피드");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<>(resultMap, status);
			}
			logger.debug("피드 검색 결과 : {}", "성공");
			resultMap.put("keyword", searchResult.get("keyword"));
			resultMap.put("feeds", searchResult.get("feeds"));
			resultMap.put("minFeedId", searchResult.get("minFeedId"));
			resultMap.put("isLast", searchResult.get("isLast"));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("피드 검색 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
	@ApiOperation(value = "발자취 보기", notes = "특정 UserPlaylist 안에 있는 Feed 들 불러오는 API", response = Map.class)
	@GetMapping("/search/{userId}/{userPlaylistId}")
	public ResponseEntity<?> getFootprints(
			@PathVariable @ApiParam(value = "검색하는 유저 ID", required = true) String userId,
			@PathVariable @ApiParam(value = "조회하고자 하는 유저플레이리스트 ID", required = true) Long userPlaylistId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			List<FeedDto> footprints = feedService.getFootprints(userId, userPlaylistId);
			if (footprints == null) {
				logger.debug("피드 검색 결과 : {}", "피드 존재하지 않음");
				resultMap.put("feeds", null);
				resultMap.put("message", "존재하지 않는 피드");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<>(resultMap, status);
			}
			logger.debug("피드 검색 결과 : {}", "성공");
			resultMap.put("feeds", footprints);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("피드 검색 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
		
	}
	
}
