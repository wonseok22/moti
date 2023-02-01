package com.main.feed.model.repository;

import com.main.feed.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Comment findByCommentId (Long commentId);
	int deleteByCommentId (Long commentId);
	List<Comment> findAllByFeed_FeedId (Long feedId);
}
