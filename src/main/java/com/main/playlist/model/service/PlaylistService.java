package com.main.playlist.model.service;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.Mission;

import java.sql.SQLException;
import java.util.List;

public interface PlaylistService {
    List<UserPlaylistDto> getUserPlaylist(String userId) throws SQLException; //내 플레이리스트 조회
    List<PlaylistDto> getPlaylist(Long categoryNo) throws SQLException; //카테고리별 플레이리스트 조회

    List<Mission> getMyMissions(String userId, Long playlistNo) throws SQLException;  //내 플레이리스트 상세보기

    List<Mission> getMissions(Long playlistNo) throws SQLException;  //플레이리스트 상세보기
    
    int registMyPlaylist(String userId, Long playlistNo) throws SQLException;  //내 플레이리스트 추가
}
