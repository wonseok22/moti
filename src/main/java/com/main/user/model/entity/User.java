package com.main.user.model.entity;


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
    @JoinColumn(name="profile_id")
    private Profile profile;
    
    @OneToMany
    @JoinColumn(name="userId")
    private List<UserPlaylist> playlists = new ArrayList<>();

}
