package com.main.flower.model.service;

import com.main.flower.model.entity.Flower;
import com.main.flower.model.repository.FlowerRepository;
import com.main.util.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FlowerServiceImpl implements FlowerService {
	
	@Autowired
	FlowerRepository flowerRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Override
	public Flower registerFlower (MultipartFile image) {
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
