package com.main.flower.model.service;

import com.main.flower.model.entity.Flower;
import com.main.flower.model.repository.FlowerRepository;
import com.main.playlist.model.dto.PlaylistDto;
import com.main.playlist.model.dto.UserPlaylistDto;
import com.main.playlist.model.entity.Playlist;
import com.main.playlist.model.entity.UserPlaylist;
import com.main.playlist.model.repository.PlaylistRepository;
import com.main.playlist.model.repository.UserPlaylistRepository;
import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import com.main.util.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
	@Autowired
	FlowerRepository flowerRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Override
	public Flower registFlower(MultipartFile image) throws SQLException {
		
		try {
			Flower flower = new Flower();
			String ImagePath = s3Upload.uploadFiles(image, "flowerImages");
			flower.setFlowerImageUrl(ImagePath);
			return flowerRepository.save(flower);
			
		} catch (Exception e) {
			System.err.println("꽃 이미지 업로드 중 에러 발생");
			e.printStackTrace();
		}
		
		return null;
	}
}
