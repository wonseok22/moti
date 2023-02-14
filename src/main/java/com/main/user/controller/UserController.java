package com.main.user.controller;

import com.main.user.model.dto.MailMessage;
import com.main.user.model.dto.MailPost;
import com.main.user.model.dto.SocialLoginDto;
import com.main.user.model.dto.UserDto;
import com.main.user.model.entity.User;
import com.main.user.model.service.JwtService;
import com.main.user.model.service.MailService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/users")
@Api(tags = {"회원 관리 API"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private JwtService jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ALREADY_EXIST = "already exists";
	
	@ApiOperation(value = "회원가입", notes = "회원가입 요청 API", response = Map.class)
	@PostMapping("")
	public ResponseEntity<?> registerUser(
			@RequestBody @ApiParam(value = "회원가입 시 필요한 회원정보.", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			// Builder Pattern을 통해 user 엔티티 생성
			User user = userDto.toEntity();
			User result = userService.registerUser(user);
			if (result != null) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
				// 회원가입 성공한 경우, 성공 메시지 반환, 200 응답 코드
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
				// 회원가입 실패한 경우 실패 메시지 반환, 회워 정보 유효 X,  204 응답 코드
			}
		} catch (Exception e) {
			logger.error("회원가입 실패 : {}", e);
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			// 회원가입 중 에러 발생한 경우 실패 메시지 반환, 500 응답 코드
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "로그인", notes = "access-token, Refresh-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보.", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			User loginUser = userService.loginUser(userDto.toEntity());
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());// key, data
				userService.saveRefreshToken(userDto.toEntity().getUserId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("userId", loginUser.getUserId());
				resultMap.put("nickname", loginUser.getNickname());
				resultMap.put("type", loginUser.getType());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				logger.debug("비밀번호 에러");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "소셜로그인", notes = "access-token, Refresh-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/socialLogin")
	public ResponseEntity<?> socialLogin(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보.", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			User loginUser = userDto.toEntity();
			
			String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
			String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());// key, data
			SocialLoginDto user = userService.socialLogin(loginUser, refreshToken);
			
			if(user != null) {
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("userId", user.getUserId());
				resultMap.put("nickname", user.getNickname());
				resultMap.put("type", user.getType());
				resultMap.put("initial",user.isInitial());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
			else {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message",ALREADY_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "로그아웃", notes = "로그아웃하는 유저의 Refresh Token을 삭제한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logoutUser(
			@PathVariable @ApiParam(value = "로그아웃 할 유저의 ID", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			userService.delRefreshToken(userId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	
	@ApiOperation(value = "회원정보 수정", notes = "회원정보를 수정한다.", response = Map.class)
	@PutMapping("")
	public ResponseEntity<?> modifyUser(
			@RequestBody @ApiParam(value = "수정하려는 회원정보", required = true) UserDto userDto, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			try {
				User result = userService.modifyUser(userDto.toEntity());
				if (result != null) {
					// 회원정보 수정 성공한 경우, 성공 메시지 반환, 200 응답 코드
					resultMap.put("message", SUCCESS);
					logger.debug("수정된 회원정보 : {}", userDto);
					status = HttpStatus.OK;
				} else {
					// 회원정보 수정  실패한 경우 실패 메시지 반환, 회워 정보 유효 X,  204 응답 코드
					resultMap.put("message", FAIL);
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				// 서버 에러 발생한 경우 실패 메시지 반환, 500 응답 코드
				logger.error("회원정보 수정 실패 : {}", e);
				resultMap.put("message", FAIL);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("access-token 사용 불가능, 재발급 요청");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "회원탈퇴", notes = "회원정보를 삭제한다.", response = Map.class)
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(
			@PathVariable @ApiParam(value = "탈퇴할 회원 정보", required = true) String userId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String access_token = request.getHeader("access-token");
		
		if (access_token == null || "".equals(access_token)) {
			logger.error("need access-token : {}");
			resultMap.put("message", FAIL);
			return new ResponseEntity<>(resultMap, status);
		}
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("회원 탈퇴: 사용 가능한 access-token");
			logger.info("삭제하려는 ID : {}", userId);
			try {
				int result = userService.deleteUser(userId);
				if (result == 1) {
					// 회원삭제 성공한 경우, 성공 메시지 반환, 200 응답 코드
					logger.debug("탈퇴된 회원 Id : {}", userId);
					resultMap.put("message", SUCCESS);
					status = HttpStatus.OK;
				} else {
					// 회원삭제 실패한 경우 실패 메시지 반환, 회원 정보 유효 X,  204 응답 코드
					resultMap.put("message", FAIL);
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				// 서버에서 에러 발생한 경우 실패 메시지 반환, 500 응답 코드
				logger.error("회원탈퇴 중 에러 발생 : {}", e);
				resultMap.put("message", FAIL);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("access-token 사용 불가능, 재발급 요청");
			resultMap.put("message", FAIL);
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "회원정보 조회", notes = "회원정보를 반환한다.", response = Map.class)
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(
			@PathVariable @ApiParam(value = "정보가 필요한 회원의 ID", required = true) String userId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		// 전송받은 Access-Token이 유효할 때만 회원정보를 조회할 수 있음.
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("회원정보 조회 : 사용 가능한 Access-Token");
			try {
				User user = userService.getUser(userId); // 유저정보 받아오기
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} catch (Exception e) {
				logger.error("회원정보 조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("access-token 사용 불가능, 재발급 요청");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "access-token 유효성 검사", notes = "access-token 유효성 검사.", response = Map.class)
	@PostMapping("/check")
	public ResponseEntity<?> checkToken(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		String token = request.getHeader("access-token");

		//액세스토큰이 유효한지 확인한 후 결과 반환.
		if (jwtService.checkToken(token)) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} else {
			logger.debug("refresh-token 만료.");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "access-token 재발급", notes = "만료된 access-token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, userDto : {}", token, userDto);
		
		// RefreshToken을 받으면 이 토큰이 유효한지 확인한 후 AccessToken을 재발급한다.
		if (jwtService.checkToken(token)) {
			String refreshToken = userService.getRefreshToken(userDto.getUserId());
			if (refreshToken == null) {
				// 잘못된 유저정보로 토큰 요청한 경우
				resultMap.put("message", FAIL);
				return new ResponseEntity<>(resultMap, status);
			}
			if (token.equals(refreshToken)) {
				String accessToken = jwtService.createAccessToken("userId", userDto.getUserId());
				logger.debug("refresh-token : {}", accessToken);
				logger.debug("access-token 재발급 완료.");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
		} else {
			logger.debug("refresh-token 만료.");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "중복검사", notes = "Id, Nickname, Email 중복검사 실시", response = Map.class)
	@GetMapping("/check")
	public ResponseEntity<?> checkDuplicate(@RequestParam String type, @RequestParam String value) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			User result = userService.checkUser(type, value);
			if (result != null) {
				// 존재하는 값인 경우, 200응답코드와 중복 메시지 반환
				resultMap.put("message", ALREADY_EXIST);
			} else {
				// 200 응답코드와 정상 메시지 반환
				resultMap.put("message", SUCCESS);
			}
				status = HttpStatus.OK;
		} catch (Exception e) {
			// 서버 에러 발생한 경우 실패 메시지 반환, 500 응답 코드
			logger.error("중복 확인중 에러 발생 : {}", e);
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "이메일본인인증요청", notes = "email을 통해 인증번호 요청해 본인인증", response = Map.class)
	@PostMapping("/email")
	public ResponseEntity<?> validateEmail(@RequestBody MailPost mailPost) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		MailMessage mailMessage = MailMessage.builder()
				.to(mailPost.getEmail())
				.subject("[Moti] 이메일 인증을 위한 인증 코드 발송")
				.build();
		
		String code = mailService.sendMail(mailMessage, "email");
		if (code != null) {
			status = HttpStatus.OK;
			resultMap.put("message", SUCCESS);
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			resultMap.put("message", FAIL);
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "이메일본인인증확인", notes = "email을 통해 인증번호 요청해 본인인증", response = Map.class)
	@GetMapping("/email")
	public ResponseEntity<?> validateEmailCheck(@RequestParam String code) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		if (mailService.checkCode(code)) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.REQUEST_TIMEOUT;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "본인인증 여부 확인", notes = "본인인증이 되었는지 확인", response = Map.class)
	@GetMapping("/auth")
	public ResponseEntity<?> checkAuthentication(@RequestParam String email) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		if (mailService.checkAuthentication(email)) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.REQUEST_TIMEOUT;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 검색", notes = "검색어 기반 회원 검색 API", response = Map.class)
	@GetMapping("/search/{keyword}/{pageNo}")
	public ResponseEntity<?> searchUser (
			@PathVariable @ApiParam(value = "검색어", required = true) String keyword,
			@PathVariable @ApiParam(value = "페이지 번호(0부터 시작)", required = true) int pageNo) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			Map<String, Object> searchResult = userService.searchUser(keyword, pageNo);
			if (searchResult == null) {
				logger.debug("회원 검색 결과 : {}", "유저 존재하지 않음");
				resultMap.put("users", null);
				resultMap.put("message", "존재하지 않는 유저");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<>(resultMap, status);
			}
			logger.debug("회원 검색 결과 : {}", "성공");
			resultMap.put("users", searchResult.get("users"));
			resultMap.put("isLast", searchResult.get("isLast"));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("회원 검색 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
}
