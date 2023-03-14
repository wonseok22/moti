package com.main.feed.model.service;

import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.dto.WriteCommentDto;
import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Comment;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.entity.Like;
import com.main.user.model.dto.SearchUserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FeedService {
	Feed writeFeed(WriteFeedDto writeFeedDto, List<MultipartFile> images);
	
	FeedDto viewFeed(Long feedId, String userId);
	
	Feed modifyFeed (Long feedId, String content);
	
	int deleteFeed(Long feedId);
	
	Comment writeComment(WriteCommentDto writeCommentDto);
	
	int deleteComment(Long commentId);
	
	Like addLike(String userId, Long feedId);
	
	int deleteLike(String userId, Long feedId);
	
	Map<String, Object> searchFeed (String userId, String content, String kind, Long minFeedId);
	
	List<FeedDto> getFootprints (String userId, Long userPlaylistId);
	
	List<SearchUserDto> getLike (Long feedId);
}
