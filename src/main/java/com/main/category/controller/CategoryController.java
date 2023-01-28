package com.main.category.controller;

import com.main.category.model.dto.CategoryDto;
import com.main.category.model.service.CategoryService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Api(tags = {"카테고리 관리 API"})
@RequestMapping("/category")
public class CategoryController {
    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ALREADY_EXIST = "already exists";
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "카테고리 조회", notes = "카테고리 요청 API", response = Map.class)
    @GetMapping("/")
    public ResponseEntity<?> getCategory() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            List<CategoryDto> categories = categoryService.getCategories();

                logger.debug("카테고리 목록 : {}", categories);
                resultMap.put("categories", categories);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.OK;
                
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("카테고리 조회 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "카테고리별 플레이리스트 조회", notes = "카테고리별 플레이리스트 요청 API", response = Map.class)
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getPlaylist(
            @PathVariable @ApiParam(value = "플레이리스트 조회에 필요한 카테고리 번호.", required = true)Long categoryId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            List<PlaylistDto> playlists = categoryService.getPlaylists(categoryId);

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
}
