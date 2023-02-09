package com.main.feed.model.repository;

import com.main.feed.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	int deleteByUser_UserIdAndFeed_FeedId(String userId, Long feedId);
	
	Like findByFeed_FeedIdAndUser_UserId(Long feedId, String userId);
	
	int deleteAllByFeed_FeedId (Long feedId);
	
	int deleteAllByUser_UserId (String userId);
}
