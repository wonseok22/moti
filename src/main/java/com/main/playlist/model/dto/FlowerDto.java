package com.main.playlist.model.dto;

import com.main.playlist.model.entity.Flower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FlowerDto {
    private Long flowerId;
    
    private String flowerImageUrl;

    public static FlowerDto toDto(Flower flower){
        return new FlowerDto(
                flower.getFlowerId(),
                flower.getFlowerImageUrl()
        );
    }
}
