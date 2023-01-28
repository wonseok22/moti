package com.main.feed.model.service;

import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Feed;

import java.sql.SQLException;

public interface FeedService {
	Feed writeFeed(WriteFeedDto writeFeedDto) throws SQLException;
}
