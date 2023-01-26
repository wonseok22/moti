package com.main.playlist.model.service;

import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.*;
import com.main.playlist.model.respository.CategoryRepository;
import com.main.playlist.model.respository.PlaylistRepository;
import com.main.playlist.model.respository.UserPlaylistRepository;
import com.main.user.model.dto.UserDto;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UserPlaylistRepository userPlaylistRepository;
    @Override
    public List<UserPlaylistDto> getUserPlaylist(String userId) throws SQLException {
        List<UserPlaylistDto> userPlaylists = new ArrayList<>();
        userPlaylistRepository.findByUser_UserId(userId).forEach(x->userPlaylists.add(UserPlaylistDto.toDto(x)));
        return userPlaylists;
    }

    @Override
    public List<PlaylistDto> getPlaylist(Long categoryNo) throws SQLException {
        Category category = categoryRepository.findByCategoryNo(categoryNo);
        List<PlaylistDto> playlists = new ArrayList<>();
        category.getPlaylists().forEach(x->playlists.add(PlaylistDto.toDto(x)));
        return playlists;
    }

    @Override
    public List<Mission> getMyMissions(String userId, Long playlistNo) throws SQLException {
        
        return UserPlaylistDto.toDto(userPlaylistRepository.findByUser_UserIdAndPlaylist_PlaylistNo(userId,playlistNo)).getPlaylist().getMissions();
    }

    @Override
    public List<Mission> getMissions(Long playlistNo) throws SQLException {
        Playlist playlist = playlistRepository.findByPlaylistNo(playlistNo);
        List<Mission> missions = new ArrayList<Mission>();
        for (PlaylistMission playlistMission : playlist.getPlaylistMissions()) {
            missions.add(playlistMission.getMission());
        }

        return missions;
    }

    @Override
    public int registMyPlaylist(String userId, Long playlistNo) throws SQLException {
        User user = userRepository.findByUserId(userId);
        Playlist playlist = playlistRepository.findByPlaylistNo(playlistNo);
        UserPlaylist userPlaylist = new UserPlaylist();
        userPlaylist.setPlaylist(playlist);

        return 0;
    }
}
