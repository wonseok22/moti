package com.main.playlist.model.dto;

import com.main.playlist.model.entity.Flower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FlowerDto {
    private Long id;
    private String name;
    private String meaning;

    public static FlowerDto toDto(Flower flower){
        return new FlowerDto(
                flower.getId(),
                flower.getName(),
                flower.getMeaning()
        );
    }
}
