package com.main.playlist.model.repository;

import com.main.playlist.model.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    public Playlist findByPlaylistId(Long playlistId);
	Playlist findByPlaylistNameLike(String s);
}
