package com.main.playlist.model.service;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.*;
import com.main.playlist.model.respository.PlaylistRepository;
import com.main.playlist.model.respository.UserPlaylistRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService{
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
        userPlaylist.setEndDate(LocalDateTime.now().plusDays(7));
        
        userPlaylistRepository.save(userPlaylist);

        return userPlaylist;
    }
}
