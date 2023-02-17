package com.main.feed.model.repository;

import com.main.feed.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	int deleteByCommentId(Long commentId);
	
	int deleteAllByFeed_FeedId (Long feedId);
	
	int deleteAllByUser_UserId (String userId);
}
