package com.main.playlist.model.repository;

import com.main.playlist.model.entity.UserPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface UserPlaylistRepository extends JpaRepository<UserPlaylist,Long> {
	public UserPlaylist findByUserPlaylistId(Long userPlaylistId);
	public List<UserPlaylist> findByUser_UserIdAndEndDateAfter(String userId, LocalDateTime endDate);
}
