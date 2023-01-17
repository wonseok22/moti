package com.main.user.model.service;

import com.main.user.model.entity.User;

import java.sql.SQLException;
import java.util.List;


public interface UserService {

    User getUser(String userId) throws SQLException; // 회원 정보 상세보기
    User registUser(User user) throws Exception; // 회원 가입
    User loginUser(User user) throws Exception; // 회원 로그인
    User modifyUser(User user) throws Exception; // 회원 정보수정
    int deleteUser(String userId) throws Exception; // 회원 삭제
    User checkMember(String type, String value) throws Exception;


    public void saveRefreshToken(String userId, String refreshToken) throws Exception;
    public String getRefreshToken(String userId) throws Exception;
    public void delRefreshToken(String userId) throws Exception;

    List<User> getAllUser();
}

