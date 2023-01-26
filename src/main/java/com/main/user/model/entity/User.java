package com.main.user.model.entity;


import com.main.feed.model.entity.Comment;
import com.main.feed.model.entity.Feed;
import com.main.feed.model.entity.Like;
import com.main.playlist.model.entity.UserPlaylist;
import com.main.profile.model.entity.Profile;
import lombok.*;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private String refreshToken;
    private String type;
    private String salt;
    private int temporary;
    private String joinDate;

    @OneToOne
    @JoinColumn(name="profileId")
    private Profile profile;
    
    @OneToMany(mappedBy = "user")
    private List<UserPlaylist> playlists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Feed> feeds = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();
    
}
