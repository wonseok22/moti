package com.main.feed.model.service;

import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Feed;

import java.sql.SQLException;

public interface FeedService {
	Feed writeFeed(WriteFeedDto writeFeedDto) throws SQLException;
	FeedDto viewFeed(Long feedId) throws SQLException;
	Feed modifyFeed(Long feedId, String content) throws SQLException;
	int deleteFeed(Long feedId) throws SQLException;
}
