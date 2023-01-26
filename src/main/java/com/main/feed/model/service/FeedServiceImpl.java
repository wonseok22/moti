package com.main.feed.model.service;

import com.main.feed.model.repository.CommentRepository;
import com.main.feed.model.repository.FeedRepository;
import com.main.feed.model.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
}
