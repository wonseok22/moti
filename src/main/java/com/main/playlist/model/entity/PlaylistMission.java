package com.main.playlist.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaylistMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistMissionId;

    private Long playlistId;

    @ManyToOne
    @JoinColumn(name="missionId", updatable = false, insertable = false)
    private Mission mission;
    
    @ManyToOne
    @JoinColumn(name = "playlistId", updatable = false, insertable = false)
    private Playlist playlist;

}
