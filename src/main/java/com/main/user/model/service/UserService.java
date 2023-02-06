package com.main.user.model.service;

import com.main.user.model.entity.User;

import java.util.Map;


public interface UserService {
	
	User getUser(String userId); // 회원 정보 상세보기
	
	User registerUser (User user) throws Exception; // 회원 가입
	
	User loginUser(User user) throws Exception; // 회원 로그인
	
	User modifyUser(User user) throws Exception; // 회원 정보수정
	
	int deleteUser(String userId); // 회원 삭제
	
	User checkUser(String type, String value);
	
	void saveRefreshToken(String userId, String refreshToken);
	
	String getRefreshToken(String userId);
	
	void delRefreshToken(String userId);
	
	Map<String, Object> searchUser(String keyword, int pageNo);
}

