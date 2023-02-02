package com.main.feed.model.service;

import com.main.category.model.repository.CategoryRepository;
import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.dto.WriteCommentDto;
import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Comment;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.entity.FeedImage;
import com.main.feed.model.entity.Like;
import com.main.feed.model.repository.CommentRepository;
import com.main.feed.model.repository.FeedRepository;
import com.main.feed.model.repository.FeedImageRepository;
import com.main.feed.model.repository.LikeRepository;
import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.UserPlaylist;
import com.main.playlist.model.repository.MissionRepository;
import com.main.playlist.model.repository.PlaylistRepository;
import com.main.playlist.model.repository.UserPlaylistRepository;
import com.main.user.model.repository.UserRepository;
import com.main.util.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedServiceImpl implements FeedService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserPlaylistRepository userPlaylistRepository;
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private FeedImageRepository feedImageRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Override
	public Feed writeFeed(WriteFeedDto writeFeedDto, List<MultipartFile> images) throws SQLException {
		Feed feed = new Feed();
		Mission mission = missionRepository.findByMissionId(writeFeedDto.getMissionId());
		UserPlaylist upl = userPlaylistRepository.findByUserPlaylistId(writeFeedDto.getUserPlaylistId());
		feed.setUser(userRepository.findByUserId(writeFeedDto.getUserId()));
		feed.setUserPlaylist(upl);
		feed.setMission(mission);
		feed.setCategory(upl.getPlaylist().getCategory());
		feed.setCreatedDate(LocalDateTime.now());
		feed.setContent(writeFeedDto.getContent());
		feedRepository.save(feed);
		
		// 이미지 처리
		if (!images.get(0).isEmpty()) {
			images.forEach(x -> {
				try {
					String imagePath = s3Upload.uploadFiles(x, "feedImages");
					FeedImage feedImage = new FeedImage();
					feedImage.setFeed(feed);
					feedImage.setFeedImageUrl(imagePath);
					feedImageRepository.save(feedImage);
				} catch (IOException e) {
					System.err.println("사진 업로드 중 에러 발생");
					e.printStackTrace();
				}
			});
		}
		return feed;
	}
	
	@Override
	public FeedDto viewFeed(Long feedId, String userId) throws SQLException {
		Feed feed = feedRepository.findByFeedId(feedId);
		
		// 해당 feedId의 피드가 존재하지 않으면 null 반환
		if (feed == null) return null;
		
		FeedDto feedDto = FeedDto.toDto(feed);
		Like like = likeRepository.findByFeed_FeedIdAndUser_UserId(feedId, userId);
		if (like != null) feedDto.setHit(true);
		return feedDto;
	}
	
	@Override
	public Feed modifyFeed(Long feedId, String content, List<MultipartFile> images) throws SQLException {
		Feed feed = feedRepository.findByFeedId(feedId);
		
		// 해당 feedId의 피드가 존재하지 않으면 null 반환
		if (feed == null) return null;
		
		feed.setContent(content);
		if (!images.get(0).isEmpty()) {
			// 이미지가 새로 업로드 되면 있던 사진 모두 삭제
			feedImageRepository.findAllByFeed_FeedId(feedId).forEach(x -> {
				try {
					s3Upload.fileDelete(x.getFeedImageUrl().split(".com/")[1]);
				} catch (Exception e) {
					System.err.println("사진 삭제 중 에러 발생");
					e.printStackTrace();
				}
				feedImageRepository.delete(x);
			});
			
			// 새 이미지 서버에 업로드 후 DB에 주소 저장
			images.forEach(x -> {
				try {
					String imagePath = s3Upload.uploadFiles(x, "feedImages");
					FeedImage feedImage = new FeedImage();
					feedImage.setFeed(feed);
					feedImage.setFeedImageUrl(imagePath);
					feedImageRepository.save(feedImage);
				} catch (IOException e) {
					System.err.println("사진 업로드 중 에러 발생");
					e.printStackTrace();
				}
			});
		}
		return feedRepository.save(feed);
	}
	
	@Override
	@Transactional
	public int deleteFeed(Long feedId) throws SQLException {
		Feed feed = feedRepository.findByFeedId(feedId);
		
		// 해당 feedId의 피드가 존재하지 않으면 -1 반환
		if (feed == null) return -1;
		
		// 사진, 댓글, 좋아요 모두 삭제 후 피드 삭제 해야 함
		feedImageRepository.findAllByFeed_FeedId(feedId).forEach(x -> feedImageRepository.delete(x));
		commentRepository.findAllByFeed_FeedId(feedId).forEach(x -> commentRepository.delete(x));
		likeRepository.findAllByFeed_FeedId(feedId).forEach(x -> likeRepository.delete(x));
		return feedRepository.deleteByFeedId(feedId);
	}
	
	@Override
	public Comment writeComment(WriteCommentDto writeCommentDto) throws SQLException {
		Comment comment = new Comment();
		comment.setFeed(feedRepository.findByFeedId(writeCommentDto.getFeedId()));
		comment.setUser(userRepository.findByUserId(writeCommentDto.getUserId()));
		comment.setContent(writeCommentDto.getContent());
		comment.setCreatedDate(LocalDateTime.now());
		return commentRepository.save(comment);
	}
	
	@Override
	@Transactional
	public int deleteComment(Long commentId) throws SQLException {
		return commentRepository.deleteByCommentId(commentId);
	}
	
	@Override
	public Like addLike(String userId, Long feedId) {
		// 이미 눌린 상태면 그냥 그대로 반환
		Like check = likeRepository.findByFeed_FeedIdAndUser_UserId(feedId, userId);
		if (check != null) return check;
		
		Like like = new Like();
		like.setUser(userRepository.findByUserId(userId));
		like.setFeed(feedRepository.findByFeedId(feedId));
		return likeRepository.save(like);
	}
	
	@Override
	@Transactional
	public int deleteLike(String userId, Long feedId) throws SQLException {
		return likeRepository.deleteByUser_UserIdAndFeed_FeedId(userId, feedId);
	}
	
	@Override
	public Map<String, Object> searchFeed(String userId, String keyword, String kind, int pageNo) {
		// 검색 종류에 따른 분기
		if ("playlist".equals(kind)) {
			// 조회 결과 받아옴
			Map<String, Object> searchResult = new HashMap<>();
			PlaylistDto playlistDto = PlaylistDto.toDto(playlistRepository.findByPlaylistNameLike("%" + keyword + "%"));
			List<UserPlaylistDto> userPlaylists = new ArrayList<>();
			userPlaylistRepository.findByPlaylist_PlaylistId(playlistDto.getPlaylistId()).forEach(x -> userPlaylists.add(UserPlaylistDto.toDto(x)));
			List<Long> userPlaylistIds = new ArrayList<>();
			userPlaylists.forEach(x -> userPlaylistIds.add(x.getPlaylist().getPlaylistId()));
			Slice<Feed> list = feedRepository.findAllByUserPlaylist_UserPlaylistIdIn(userPlaylistIds, PageRequest.of(pageNo, 10));
			
			// DTO에 담아서 리스트에 삽입
			List<FeedDto> feeds = new ArrayList<>();
			for (Feed feed : list) {
				feeds.add(FeedDto.toDto(feed));
			}
			
			// 피드마다 좋아요 눌렀는지 확인
			for (FeedDto feedDto : feeds) {
				Like like = likeRepository.findByFeed_FeedIdAndUser_UserId(feedDto.getFeedId(), userId);
				if (like != null) feedDto.setHit(true);
			}
			
			searchResult.put("feeds", feeds);
			searchResult.put("isLast", list.isLast()); // true or false
			return searchResult;
			
		} else if ("content".equals(kind)) {
			// 조회 결과 받아옴
			Map<String, Object> searchResult = new HashMap<>();
			Slice<Feed> list = feedRepository.findAllByContentLike("%" + keyword + "%", PageRequest.of(pageNo, 10));
			
			// DTO에 담아서 리스트에 삽입
			List<FeedDto> feeds = new ArrayList<>();
			for (Feed feed : list) {
				feeds.add(FeedDto.toDto(feed));
			}
			
			// 피드마다 좋아요 눌렀는지 확인
			for (FeedDto feedDto : feeds) {
				Like like = likeRepository.findByFeed_FeedIdAndUser_UserId(feedDto.getFeedId(), userId);
				if (like != null) feedDto.setHit(true);
			}
			
			searchResult.put("feeds", feeds);
			searchResult.put("isLast", list.isLast()); // true or false
			return searchResult;
		}
		
		return null;
	}
	
}
