package com.main.category.model.service;

import com.main.category.model.dto.CategoryDto;
import com.main.category.model.entity.Category;
import com.main.category.model.repository.CategoryRepository;
import com.main.playlist.model.dto.PlaylistDto;
import com.main.util.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private S3Upload s3Upload;
	
	@Override
	public Category registerCategory (Category category, MultipartFile image) throws SQLException {
		try {
			String ImagePath = s3Upload.uploadFiles(image, "categoryImages");
			category.setCategoryImageUrl(ImagePath);
			return categoryRepository.save(category);
		} catch (Exception e) {
			System.err.println("카테고리 이미지 업로드 중 에러 발생");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<CategoryDto> getCategories() throws SQLException {
		List<CategoryDto> categories = new ArrayList<>();
		
		categoryRepository.findAll().forEach(x -> categories.add(CategoryDto.toDto(x)));
		
		return categories;
	}
	
	@Override
	public List<PlaylistDto> getPlaylists(Long categoryId) throws SQLException {
		List<PlaylistDto> playlists = new ArrayList<>();
		
		categoryRepository.findByCategoryId(categoryId).getPlaylists().forEach(x -> playlists.add(PlaylistDto.toDto(x)));
		
		return playlists;
	}
}
