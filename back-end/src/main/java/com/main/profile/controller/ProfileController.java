package com.main.profile.controller;

import com.main.profile.model.dto.ProfileDto;
import com.main.profile.model.service.ProfileService;
import com.main.user.controller.UserController;
import com.main.user.model.dto.UserDto;
import com.main.user.model.entity.User;
import com.main.user.model.service.JwtService;
import com.main.user.model.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/profile")
@Api(tags = {"프로필 관리 API"})
public class ProfileController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ALREADY_EXIST = "already exists";


    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtService jwtService;

    @ApiOperation(value = "프로필 수정", notes = "프로필 수정 API", response = Map.class)
    @PutMapping("")
    public ResponseEntity<?> modifyProfile(
              @ApiParam(value = "수정하려는 프로필 Dto", required = true)  @RequestBody ProfileDto profileDto, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if(jwtService.checkToken(request.getHeader("access-token"))) {
            try {
                int result = profileService.modifyProfile(profileDto);
                if (result == 1) {
                    // 프로필 수정 성공한 경우, 성공 메시지 반환, 200 응답 코드
                    resultMap.put("message", SUCCESS);
                    logger.debug("수정된 프로필 정보 : {}", profileDto.toString());
                    status = HttpStatus.OK;
                } else {
                    // 프로필 수정  실패한 경우 실패 메시지 반환, 회워 정보 유효 X,  204 응답 코드
                    resultMap.put("message", FAIL);
                    status = HttpStatus.ACCEPTED;
                }
            } catch (Exception e) {
                logger.error("프로필 수정 실패 : {}", e);
                resultMap.put("message", FAIL);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("access-token 사용 불가능, 재발급 요청");
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "프로필 수정", notes = "프로필 수정 API", response = Map.class)
    @GetMapping("")
    public ResponseEntity<?> getProfile(
            @ApiParam(value = "프로필을 요청할 유저의 ID", required = true)  @RequestParam String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            ProfileDto profileDto = profileService.getProfile(userId);
            if (profileDto != null) {
                // 유저 프로필 요청 성공한 경우
                resultMap.put("message", SUCCESS);
                resultMap.put("profile", profileDto);
                logger.debug("수정된 프로필 정보 : {}", profileDto.toString());
                status = HttpStatus.OK;
            } else {
                //유저 프로필 요청 실패한 경우, 실패 메시지 반환, 회원 정보 유효 X,  202 응답 코드
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("프로필 요청 에러: {}", e);
            resultMap.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
