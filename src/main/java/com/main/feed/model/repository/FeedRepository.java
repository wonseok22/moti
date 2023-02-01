package com.main.feed.model.repository;

import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.entity.Feed;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
	Feed findByFeedId(Long feedId);
	int deleteByFeedId(Long feedId);
	Slice<Feed> findAllByContentLike(String s, PageRequest of);
	Slice<Feed> findAllByUserPlaylist_UserPlaylistIdIn(List<Long> userPlaylistIds, PageRequest of);
}
