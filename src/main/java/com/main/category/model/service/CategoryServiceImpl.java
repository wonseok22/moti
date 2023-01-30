package com.main.category.model.service;


import com.main.category.model.dto.CategoryDto;
import com.main.category.model.repository.CategoryRepository;
import com.main.playlist.model.dto.PlaylistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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
