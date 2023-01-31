package com.main.feed.model.service;

import com.main.category.model.repository.CategoryRepository;
import com.main.feed.model.dto.FeedDto;
import com.main.feed.model.dto.FeedImageDto;
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
import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.UserPlaylist;
import com.main.playlist.model.repository.MissionRepository;
import com.main.playlist.model.repository.UserPlaylistRepository;
import com.main.user.model.repository.UserRepository;
import com.main.util.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserPlaylistRepository userPlaylistRepository;
	
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
		if (images != null || images.size() != 0) {
			System.out.println("size of list : " + images.size());
			images.forEach(x -> {
				try {
					String imagePath = s3Upload.uploadFiles(x, "feedImages");
					FeedImage feedImage = new FeedImage();
					feedImage.setFeed(feed);
					feedImage.setFeedImageUrl(imagePath);
					feedImageRepository.save(feedImage);
				} catch (IOException e) {
					System.out.println("피드에 사진 업로드 중 에러 발생");
					e.printStackTrace();
				}
			});
		}
		return feed;
	}
	
	@Override
	public FeedDto viewFeed(Long feedId) throws SQLException {
		FeedDto feedDto = FeedDto.toDto(feedRepository.findByFeedId(feedId));
//		List<FeedImage> feedImages = new ArrayList<>();
//		feedImageRepository.findAllByFeedId(feedId).forEach(x -> feedImages.add(x));
//		for (int i = 0; i < feedImages.size(); i++) {
//			feedDto.getFeedImages().add(FeedImageDto.toDto(feedImages.get(i)));
//		}
		return feedDto;
	}
	
	@Override
	public Feed modifyFeed(Long feedId, String content) throws SQLException {
		Feed feed = feedRepository.findByFeedId(feedId);
		feed.setContent(content);
//		feed.setFiles();
		return feedRepository.save(feed);
	}
	
	@Override
	@Transactional
	public int deleteFeed(Long feedId) throws SQLException {
//		Feed feed = feedRepository.findByFeedId(feedId);
		// Like, Comment, File ... 처리
		return feedRepository.deleteByFeedId(feedId);
	}
	
	@Override
	public Comment writeComment (WriteCommentDto writeCommentDto) throws SQLException {
		Comment comment = new Comment();
		comment.setFeed(feedRepository.findByFeedId(writeCommentDto.getFeedId()));
		comment.setUser(userRepository.findByUserId(writeCommentDto.getUserId()));
		comment.setContent(writeCommentDto.getContent());
		comment.setCreatedDate(LocalDateTime.now());
		return commentRepository.save(comment);
	}
	
	@Override
	@Transactional
	public int deleteComment (Long commentId) throws SQLException {
		return commentRepository.deleteByCommentId(commentId);
	}
	
	@Override
	public Like addLike (String userId, Long feedId) {
		Like like = new Like();
		like.setUser(userRepository.findByUserId(userId));
		like.setFeed(feedRepository.findByFeedId(feedId));
		return likeRepository.save(like);
	}
	
	@Override
	@Transactional
	public int deleteLike (String userId, Long feedId) throws SQLException {
		return likeRepository.deleteByUser_UserIdAndFeed_FeedId(userId, feedId);
	}
	
}
