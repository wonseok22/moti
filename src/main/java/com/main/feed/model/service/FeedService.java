package com.main.feed.model.service;

import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.dto.WriteCommentDto;
import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Comment;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.entity.Like;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FeedService {
	Feed writeFeed(WriteFeedDto writeFeedDto, List<MultipartFile> images) throws SQLException;
	
	FeedDto viewFeed(Long feedId, String userId) throws SQLException;
	
	Feed modifyFeed(Long feedId, String content, List<MultipartFile> images) throws SQLException;
	
	int deleteFeed(Long feedId) throws SQLException;
	
	Comment writeComment(WriteCommentDto writeCommentDto) throws SQLException;
	
	int deleteComment(Long commentId) throws SQLException;
	
	Like addLike(String userId, Long feedId) throws SQLException;
	
	int deleteLike(String userId, Long feedId) throws SQLException;
	
	Map<String, Object> searchFeed(String userId, String content, String kind, int pageNo) throws SQLException;
}
