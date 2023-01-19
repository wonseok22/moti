package com.main.user.model.entity;


import com.main.playlist.model.entity.UserPlaylist;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private int userNo;
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

}
