package com.main.feed.model.repository;

import com.main.feed.model.entity.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedImageRepository extends JpaRepository<FeedImage, Long> {
	List<FeedImage> findAllByFeed_FeedId(Long feedId);
}
