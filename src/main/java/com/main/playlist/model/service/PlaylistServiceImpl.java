package com.main.playlist.model.service;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.*;
import com.main.playlist.model.repository.PlaylistRepository;
import com.main.playlist.model.repository.UserPlaylistRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private UserPlaylistRepository userPlaylistRepository;
	
	@Override
	public List<UserPlaylistDto> getMyPlaylists(String userId) {
		List<UserPlaylistDto> userPlaylists = new ArrayList<>();
		
		userPlaylistRepository.findByUser_UserId(userId).forEach(x -> userPlaylists.add(UserPlaylistDto.toDto(x)));
		
		return userPlaylists;
	}
	
	@Override
	public List<UserPlaylistDto> getCurrentPlaylists(String userId) {
		List<UserPlaylistDto> userPlaylists = new ArrayList<>();
		
		userPlaylistRepository.findByUser_UserIdAndEndDateAfter(userId, LocalDateTime.now()).forEach(x -> userPlaylists.add(UserPlaylistDto.toDto(x)));
		
		return userPlaylists;
	}
	
	@Override
	public UserPlaylistDto getMyPlaylist(Long userPlaylistId) {
		return UserPlaylistDto.toDto(userPlaylistRepository.findByUserPlaylistId(userPlaylistId));
	}
	
	@Override
	public PlaylistDto getPlaylist(Long playlistId) {
		return PlaylistDto.toDto(playlistRepository.findByPlaylistId(playlistId));
	}
	
	@Override
	public UserPlaylist registerMyPlaylist (String userId, Long playlistId) {
		User user = userRepository.findByUserId(userId);
		
		Playlist playlist = playlistRepository.findByPlaylistId(playlistId);
		
		UserPlaylist userPlaylist = new UserPlaylist();
		
		userPlaylist.setPlaylist(playlist);
		userPlaylist.setUser(user);
		userPlaylist.setStartDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
		userPlaylist.setEndDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(7).truncatedTo(ChronoUnit.DAYS));

		userPlaylistRepository.save(userPlaylist);
		
		return userPlaylist;
	}
	
	@Override
	public UserPlaylist replayPlaylist(Long userPlaylistId) {
		UserPlaylist userPlaylist = userPlaylistRepository.findByUserPlaylistId(userPlaylistId);
		
		userPlaylist.setEndDate(LocalDateTime.now().plusDays(7).truncatedTo(ChronoUnit.DAYS));

		userPlaylistRepository.save(userPlaylist);
		
		return userPlaylist;
	}
	
	@Override
	public UserPlaylist checkUserPlaylist(String userId, Long playlistId) {
		return userPlaylistRepository.findByUser_UserIdAndPlaylist_PlaylistIdAndEndDateAfter(userId, playlistId, LocalDateTime.now());
	}
}
