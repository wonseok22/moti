package com.main.playlist.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    
    @Column(nullable = false,unique = true)
    private String categoryName;
    
    private String categoryImageUrl;
    
    @OneToMany(mappedBy = "category")
    private List<Playlist> playlists = new ArrayList<>();
}
