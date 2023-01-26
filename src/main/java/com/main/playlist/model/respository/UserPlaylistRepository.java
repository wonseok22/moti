package com.main.playlist.model.respository;

import com.main.playlist.model.entity.UserPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserPlaylistRepository extends JpaRepository<UserPlaylist,Long> {
	public UserPlaylist findByUser_UserIdAndPlaylist_PlaylistNo(String userId, Long playlistNo);
	public List<UserPlaylist> findByUser_UserId(String userId);
}
