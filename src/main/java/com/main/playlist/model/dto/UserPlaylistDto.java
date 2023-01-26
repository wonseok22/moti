package com.main.playlist.model.dto;

import com.main.playlist.model.entity.Playlist;
import com.main.playlist.model.entity.UserPlaylist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class UserPlaylistDto {
    private Long userPlaylistNo;
    private String userId;
    private PlaylistDto playlist;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static UserPlaylistDto toDto(UserPlaylist userPlaylist){
        
        return new UserPlaylistDto(
                userPlaylist.getUserPlaylistNo(),
                userPlaylist.getUser().getUserId(),
                PlaylistDto.toDto(userPlaylist.getPlaylist()),
                userPlaylist.getStartDate(),
                userPlaylist.getEndDate()
        );
    }

}
