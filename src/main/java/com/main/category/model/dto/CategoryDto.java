package com.main.category.model.dto;

import com.main.category.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    private Long categoryId;

    private String categoryName;

    private String categoryImageUrl;

    private int current;


    public static CategoryDto toDto(Category category){
        AtomicInteger currentCnt = new AtomicInteger();

        category.getPlaylists().forEach(playlist -> playlist.getUserPlaylists().forEach(p->{
                    if(p.getEndDate().isAfter(LocalDateTime.now()))
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

