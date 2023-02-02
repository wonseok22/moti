package com.main.category.model.service;

import com.main.category.model.dto.CategoryDto;
import com.main.playlist.model.dto.PlaylistDto;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
	List<CategoryDto> getCategories() throws SQLException;
	
	List<PlaylistDto> getPlaylists(Long categoryId) throws SQLException; //카테고리별 플레이리스트 조회
	
}

