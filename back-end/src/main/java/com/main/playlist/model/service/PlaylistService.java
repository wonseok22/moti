package com.main.playlist.model.service;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.UserPlaylist;

import java.util.List;

public interface PlaylistService {
	List<UserPlaylistDto> getMyPlaylists(String userId); //내 모든 플레이리스트 조회
	
	List<UserPlaylistDto> getCurrentPlaylists(String userId); //내가 수행중인 플레이리스트 조회
	
	UserPlaylistDto getMyPlaylist(Long playlistId);  //내 플레이리스트 상세보기
	
	PlaylistDto getPlaylist(Long playlistId);  //플레이리스트 상세보기
	
	UserPlaylist registerMyPlaylist(String userId, Long playlistId);  //내 플레이리스트 추가
	
	UserPlaylist replayPlaylist(Long userPlaylistId);  //내 플레이리스트 추가
	
	UserPlaylist checkUserPlaylist(String userId, Long playlistId);
}

