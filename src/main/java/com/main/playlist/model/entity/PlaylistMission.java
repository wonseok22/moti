package com.main.playlist.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaylistMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="playlistNo")
//    private Playlist playlist;
    private Long playlistNo;

    @ManyToOne
    @JoinColumn(name="missionNo")
    private Mission mission;

}
