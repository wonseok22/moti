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
	
	Slice<Feed> findAllByUser_UserIdInAndFeedIdLessThanOrderByFeedIdDesc (List<String> followingList, Long minFeedId, PageRequest of);
	
	Slice<Feed> findAllByUser_UserIdNotInAndFeedIdLessThanOrderByFeedIdDesc (List<String> followingList, Long minFeedId, PageRequest of);
	
	Slice<Feed> findAllByUserPlaylist_UserPlaylistIdInAndFeedIdLessThanOrderByFeedIdDesc (List<Long> userPlaylistIds, Long minFeedId, PageRequest of);
	
	Slice<Feed> findAllByContentLikeAndFeedIdLessThanOrderByFeedIdDesc (String s, Long minFeedId, PageRequest of);
	
	Slice<Feed> findAllByUser_UserIdAndFeedIdLessThanOrderByFeedIdDesc (String keyword, Long minFeedId, PageRequest of);
	
	List<Feed> findAllByUser_UserId (String userId);
}
