package com.main.playlist.model.dto;

import com.main.playlist.model.entity.Flower;
import com.main.playlist.model.entity.Mission;
import com.main.playlist.model.entity.Playlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlaylistDto {
    private Long playlistNo;
    
    private String playlistName;

    private String playlistDesc;

    private List<Mission> missions;
   
    private FlowerDto flower;

    public static PlaylistDto toDto(Playlist playlist){
        List<Mission> mission = new ArrayList<>();
        playlist.getPlaylistMissions().forEach(x->mission.add(x.getMission()));

        return new PlaylistDto(
                playlist.getPlaylistId(),
                playlist.getPlaylistName(),
                playlist.getPlaylistDesc(),
                mission,
                FlowerDto.toDto(playlist.getFlower())
        );
    }
}
