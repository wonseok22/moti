package com.main.playlist.model.entity;

import com.main.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph
public class UserPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPlaylistNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="playlistNo")
    private Playlist playlist;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="flower_image_id")
    private FlowerImage flowerImage;
}
