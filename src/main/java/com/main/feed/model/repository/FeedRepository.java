package com.main.feed.model.repository;

import com.main.feed.model.entity.Feed;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
	Feed findByFeedId(Long feedId);
	
	int deleteByFeedId(Long feedId);
	
	Slice<Feed> findAllByUserPlaylist_UserPlaylistIdInOrderByFeedIdDesc(List<Long> userPlaylistIds, PageRequest of);
	
	Slice<Feed> findAllByContentLikeOrderByFeedIdDesc (String s, PageRequest of);
	
	Slice<Feed> findAllByUser_UserIdOrderByFeedIdDesc (String userId, PageRequest of);
	
	Slice<Feed> findAllByUser_UserIdInOrderByFeedIdDesc (List<String> followingList, PageRequest of);
	
	Slice<Feed> findAllByUser_UserIdNotInOrderByFeedIdDesc (List<String> followingList, PageRequest of);
}
