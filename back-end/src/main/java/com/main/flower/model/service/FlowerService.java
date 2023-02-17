package com.main.flower.model.service;

import com.main.flower.model.entity.Flower;
import org.springframework.web.multipart.MultipartFile;

public interface FlowerService {
	Flower registerFlower (MultipartFile image);
}

