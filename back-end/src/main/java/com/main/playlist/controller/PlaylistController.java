package com.main.playlist.controller;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.UserPlaylist;
import com.main.playlist.model.service.PlaylistService;
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
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@Api(tags = {"플레이리스트 관리 API"})
@RequestMapping("/playlist")
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	public static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ALREADY_EXIST = "already exists";
	
	@ApiOperation(value = "내 모든 플레이리스트 조회", notes = "내 모든 플레이리스트 조회 요청 API", response = Map.class)
	@GetMapping("/{userId}")
	public ResponseEntity<?> getMyEveryPlaylist(
			@PathVariable @ApiParam(value = "플레이리스트 조회에 필요한 유저 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			List<UserPlaylistDto> myEveryPlaylists = playlistService.getMyPlaylists(userId);
			
			logger.debug("내 모든 플레이리스트 목록 : {}", myEveryPlaylists);
			resultMap.put("myPlaylists", myEveryPlaylists);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("내 모든 플레이리스트 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "내가 수행중인 플레이리스트 조회", notes = "수행중인 플레이리스트 요청 API", response = Map.class)
	@GetMapping("/current/{userId}")
	public ResponseEntity<?> getCurrentPlaylist(
			@PathVariable @ApiParam(value = "플레이리스트 조회에 필요한 유저 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			List<UserPlaylistDto> currentPlaylists = playlistService.getCurrentPlaylists(userId);
			
			logger.debug("수행중인 플레이리스트 목록 : {}", currentPlaylists);
			resultMap.put("currentPlaylists", currentPlaylists);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("내 플레이리스트 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "플레이리스트 등록", notes = "플레이리스트 등록 API", response = Map.class)
	@PostMapping("")
	public ResponseEntity<?> registerPlaylist(
			@PathVariable @ApiParam(value = "플레이리스트 조회에 필요한 유저 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			List<UserPlaylistDto> myPlaylists = playlistService.getMyPlaylists(userId);
			
			logger.debug("등록한 플레이리스트: {}", myPlaylists);
			resultMap.put("myPlaylists", myPlaylists);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("내 플레이리스트 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "내 플레이리스트 상세조회", notes = "해당 플레이리스트에 속한 미션들을 반환한다.", response = Map.class)
	@GetMapping("/detail/{userId}/{userPlaylistId}")
	public ResponseEntity<?> getMyMissions(
			@PathVariable @ApiParam(value = "유저의 아이디", required = true) String userId,
			@PathVariable @ApiParam(value = "상세정보가 필요한 유저플레이리스트 번호", required = true) Long userPlaylistId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		//내 플레이리스트에 속한 미션 정보 가져오기
		try {
			UserPlaylistDto myPlaylist = playlistService.getMyPlaylist(userPlaylistId);
			
			logger.debug("내 플레이리스트의 미션 목록 : {}", myPlaylist);
			resultMap.put("myPlaylist", myPlaylist);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("플레이리스트의 미션 목록 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		//내가 완료한 미션의 수도 가져오기
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "플레이리스트 상세조회", notes = "해당 플레이리스트에 속한 미션들을 반환한다.", response = Map.class)
	@GetMapping("/detail/{playlistId}")
	public ResponseEntity<?> getMissions(
			@PathVariable @ApiParam(value = "상세정보가 필요한 플레이리스트 번호", required = true) Long playlistId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		//플레이리스트에 속한 미션 정보 가져오기
		try {
			PlaylistDto playlist = playlistService.getPlaylist(playlistId);
			
			logger.debug("해당 플레이리스트의 미션 목록 : {}", playlist);
			resultMap.put("playlist", playlist);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("플레이리스트의 미션 목록 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "내 플레이리스트 등록", notes = "내 플레이리스트에 해당 플레이리스트를 등록한다.", response = Map.class)
	@PostMapping("/{userId}/{playlistId}")
	public ResponseEntity<?> registerPlaylist(
			@PathVariable @ApiParam(value = "유저 아이디", required = true) String userId
			, @PathVariable @ApiParam(value = "등록할 플레이리스트 번호", required = true) Long playlistId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		//내 플레이리스트에 등록하기
		try {
			UserPlaylist userPlaylist = playlistService.registerMyPlaylist(userId, playlistId);
			if (userPlaylist != null) {
				logger.debug("플레이리스트 등록 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				resultMap.put("userPlaylistId", userPlaylist.getUserPlaylistId());
				status = HttpStatus.OK;
			} else {
				logger.debug("플레이리스트 등록 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
				// 플레이리스트 등록에 실패한 경우 실패 메시지 반환, 202 응답 코드
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("내 플레이리스트에 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	
	@ApiOperation(value = "내 플레이리스트 재시작", notes = "해당 플레이리스트를 재시작한다.", response = Map.class)
	@PutMapping("/{userPlaylistId}")
	public ResponseEntity<?> replayPlaylist(
			@PathVariable @ApiParam(value = "재시작할 유저의 플레이리스트 번호", required = true) Long userPlaylistId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		//내 플레이리스트에 등록하기
		try {
			UserPlaylist userPlaylist = playlistService.replayPlaylist(userPlaylistId);
			
			if (userPlaylist != null) {
				logger.debug("플레이리스트 수정 결과 : {}", "성공");
				resultMap.put("message", SUCCESS);
				resultMap.put("userPlaylistId", userPlaylist.getUserPlaylistId());
				status = HttpStatus.OK;
			} else {
				logger.debug("플레이리스트 수정 결과 : {}", "실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
				// 플레이리스트 수정에 실패한 경우 실패 메시지 반환, 202 응답 코드
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("내 플레이리스트에 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "플레이리스트 중복 검사", notes = "해당 플레이리스트를 현재 수행하고있는지 확인", response = Map.class)
	@GetMapping("/check/{userId}/{playlistId}")
	public ResponseEntity<?> checkUserPlaylist(@PathVariable String userId, @PathVariable Long playlistId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			UserPlaylist result = playlistService.checkUserPlaylist(userId, playlistId);
			if (result != null) {
				// 존재하는 값인 경우, 200응답코드와 중복 메시지 반환
				resultMap.put("message", ALREADY_EXIST);
				status = HttpStatus.OK;
			} else {
				// 200 응답코드와 정상 메시지 반환
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			// 서버 에러 발생한 경우 실패 메시지 반환, 500 응답 코드
			logger.error("중복 확인중 에러 발생 : {}", e);
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}
}
