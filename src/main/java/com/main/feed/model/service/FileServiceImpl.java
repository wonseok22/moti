package com.main.feed.model.service;

import com.main.feed.model.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fileRepository;

}
