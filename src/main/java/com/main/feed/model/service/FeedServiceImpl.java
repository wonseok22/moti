package com.main.feed.model.service;

import com.main.category.model.respository.CategoryRepository;
import com.main.feed.model.dto.WriteFeedDto;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.repository.CommentRepository;
import com.main.feed.model.repository.FeedRepository;
import com.main.feed.model.repository.FileRepository;
import com.main.playlist.model.entity.UserPlaylist;
import com.main.playlist.model.respository.MissionRepository;
import com.main.playlist.model.respository.UserPlaylistRepository;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

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
	private FileRepository fileRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Feed writeFeed(WriteFeedDto writeFeedDto) throws SQLException {
		Feed feed = new Feed();
		UserPlaylist upl = userPlaylistRepository.findByUserPlaylistId(writeFeedDto.getUserPlaylistId());
		feed.setUser(userRepository.findByUserId(writeFeedDto.getUserId()));
		feed.setUserPlaylist(upl);
		feed.setMission(missionRepository.findByMissionId(writeFeedDto.getMissionId()));
		feed.setCategory(upl.getPlaylist().getCategory());
		feed.setContent(writeFeedDto.getContent());
		feed.setCreatedDate(LocalDateTime.now());
		System.out.println("feed = " + feed.toString());
		return feedRepository.save(feed);
	}
}
