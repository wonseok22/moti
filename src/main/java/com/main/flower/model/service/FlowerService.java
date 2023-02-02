package com.main.flower.model.service;

import com.main.flower.model.entity.Flower;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

public interface FlowerService {
	Flower registFlower(MultipartFile image) throws SQLException;
}

