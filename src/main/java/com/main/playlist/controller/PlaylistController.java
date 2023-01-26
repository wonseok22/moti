package com.main.playlist.controller;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.Playlist;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Api(tags = {"플레이리스트 관리 API"})
public class PlaylistController {
    public static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ALREADY_EXIST = "already exists";
    @Autowired
    private PlaylistService playlistService;

    @ApiOperation(value = "내 플레이리스트 조회", notes = "내 플레이리스트 요청 API", response = Map.class)
    @GetMapping("/playlist/{userId}")
    public ResponseEntity<?> getMyPlaylist(
            @PathVariable @ApiParam(value = "플레이리스트 조회에 필요한 유저 아이디.", required = true)String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            List<UserPlaylistDto> myPlaylists = playlistService.getMyPlaylists(userId);

                logger.debug("내 플레이리스트 목록 : {}", myPlaylists);
                resultMap.put("myPlaylists", myPlaylists);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.OK;
                
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("내 플레이리스트 조회 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    @ApiOperation(value = "카테고리별 플레이리스트 조회", notes = "카테고리별 플레이리스트 요청 API", response = Map.class)
    @GetMapping("/playlist/category/{categoryId}")
    public ResponseEntity<?> getPlaylist(
            @PathVariable @ApiParam(value = "플레이리스트 조회에 필요한 카테고리 번호.", required = true)Long categoryId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            List<PlaylistDto> playlists = playlistService.getPlaylists(categoryId);

                logger.debug("해당 카테고리의 플레이리스트 목록 : {}", playlists);
                resultMap.put("playlist", playlists);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.OK;
                
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("카테고리별 플레이리스트 조회 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "내 플레이리스트 상세조회", notes = "해당 플레이리스트에 속한 미션들을 반환한다.", response = Map.class)
    @GetMapping("/playlist/detail/{userId}/{playlistId}")
    public ResponseEntity<?> getMyMissions(
            @PathVariable @ApiParam(value = "상세정보가 필요한 플레이리스트 번호", required = true) String userId,
            @PathVariable @ApiParam(value = "상세정보가 필요한 플레이리스트 번호", required = true) Long playlistId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        //내 플레이리스트에 속한 미션 정보 가져오기
        try {
            UserPlaylistDto myPlaylist = playlistService.getMyPlaylist(userId, playlistId);
        
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

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "플레이리스트 상세조회", notes = "해당 플레이리스트에 속한 미션들을 반환한다.", response = Map.class)
    @GetMapping("/playlist/detail/{playlistId}")
    public ResponseEntity<?> getMissions(
            @PathVariable @ApiParam(value = "상세정보가 필요한 플레이리스트 번호", required = true) Long playlistId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

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

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    @ApiOperation(value = "내 플레이리스트 등록", notes = "내 플레이리스트에 해당 플레이리스트를 등록한다.", response = Map.class)
    @PostMapping("/playlist/{userId}/{playlistId}")
    public ResponseEntity<?> registPlaylist(
            @PathVariable @ApiParam(value = "내 아이디", required = true) String userId
            ,@PathVariable @ApiParam(value = "등록할 플레이리스트 번호", required = true) Long playlistId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        
        //내 플레이리스트에 등록하기
        try {
            UserPlaylist userPlaylist = playlistService.registMyPlaylist(userId,playlistId);
            if(userPlaylist != null){
                logger.debug("플레이리스트 등록 결과 : {}", "성공");
                resultMap.put("message", SUCCESS);
                status = HttpStatus.OK;
            } else {
                logger.debug("플레이리스트 등록 결과 : {}", "실패");
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
                // 플레이리스트 등록에 실패한 경우 실패 메시지 반환, 204 응답 코드
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("내 플레이리스트에 등록 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
}