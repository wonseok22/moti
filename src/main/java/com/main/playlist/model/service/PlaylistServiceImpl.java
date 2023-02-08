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

import java.sql.SQLException;
import java.time.LocalDateTime;
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
	public List<UserPlaylistDto> getMyPlaylists(String userId) throws SQLException {
		List<UserPlaylistDto> userPlaylists = new ArrayList<>();
		
		userPlaylistRepository.findByUser_UserId(userId).forEach(x -> userPlaylists.add(UserPlaylistDto.toDto(x)));
		
		return userPlaylists;
	}
	
	@Override
	public List<UserPlaylistDto> getCurrentPlaylists(String userId) throws SQLException {
		List<UserPlaylistDto> userPlaylists = new ArrayList<>();
		
		userPlaylistRepository.findByUser_UserIdAndEndDateAfter(userId, LocalDateTime.now()).forEach(x -> userPlaylists.add(UserPlaylistDto.toDto(x)));
		
		return userPlaylists;
	}
	
	@Override
	public UserPlaylistDto getMyPlaylist(Long userPlaylistId) throws SQLException {
		
		return UserPlaylistDto.toDto(userPlaylistRepository.findByUserPlaylistId(userPlaylistId));
	}
	
	@Override
	public PlaylistDto getPlaylist(Long playlistId) throws SQLException {
		
		return PlaylistDto.toDto(playlistRepository.findByPlaylistId(playlistId));
	}
	
	@Override
	public UserPlaylist registMyPlaylist(String userId, Long playlistId) throws SQLException {
		
		User user = userRepository.findByUserId(userId);
		
		Playlist playlist = playlistRepository.findByPlaylistId(playlistId);
		
		UserPlaylist userPlaylist = new UserPlaylist();
		
		userPlaylist.setPlaylist(playlist);
		userPlaylist.setUser(user);
		userPlaylist.setStartDate(LocalDateTime.now());
		userPlaylist.setEndDate(LocalDateTime.now().plusDays(8).minusSeconds(1).truncatedTo(ChronoUnit.DAYS));
		
		userPlaylistRepository.save(userPlaylist);
		
		return userPlaylist;
	}
	
	@Override
	public UserPlaylist replayPlaylist(Long userPlaylistId) throws SQLException {
		
		
		UserPlaylist userPlaylist = userPlaylistRepository.findByUserPlaylistId(userPlaylistId);
		
		
		userPlaylist.setEndDate(LocalDateTime.now().plusDays(8).minusSeconds(1).truncatedTo(ChronoUnit.DAYS));
		
		userPlaylistRepository.save(userPlaylist);
		
		return userPlaylist;
	}
	
	@Override
	public UserPlaylist checkUserPlaylist(String userId, Long playlistId) {
		return userPlaylistRepository.findByUser_UserIdAndPlaylist_PlaylistIdAndEndDateAfter(userId, playlistId, LocalDateTime.now());
	}
}
