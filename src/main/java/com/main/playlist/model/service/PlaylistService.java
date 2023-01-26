package com.main.playlist.model.service;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.UserPlaylist;

import java.sql.SQLException;
import java.util.List;

public interface PlaylistService {
    List<UserPlaylistDto> getMyPlaylists(String userId) throws SQLException; //내 플레이리스트 조회
    List<PlaylistDto> getPlaylists(Long categoryId) throws SQLException; //카테고리별 플레이리스트 조회
    
    UserPlaylistDto getMyPlaylist(String userId, Long playlistId) throws SQLException;  //내 플레이리스트 상세보기
    
    PlaylistDto getPlaylist(Long playlistId) throws SQLException;  //플레이리스트 상세보기
    
    UserPlaylist registMyPlaylist(String userId, Long playlistId) throws SQLException;  //내 플레이리스트 추가
}
