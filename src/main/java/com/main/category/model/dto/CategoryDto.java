package com.main.category.model.dto;

import com.main.category.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
	private Long categoryId;
	
	private String categoryName;
	
	private String categoryImageUrl;
	
	private int current;
	
	public static Category toEntity(CategoryDto categoryDto) {
		Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		return category;
	}
	
	public static CategoryDto toDto(Category category) {
		AtomicInteger currentCnt = new AtomicInteger();
		
		category.getPlaylists().forEach(playlist -> playlist.getUserPlaylists().forEach(p -> {
					if (p.getEndDate().isAfter(LocalDateTime.now()))
						currentCnt.getAndIncrement();
				}
		));
		
		return new CategoryDto(
				category.getCategoryId(),
				category.getCategoryName(),
				category.getCategoryImageUrl(),
				currentCnt.get()
		);
	}
}

