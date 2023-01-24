package com.main.user.model.entity;


import com.main.profile.model.entity.Profile;
import lombok.*;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


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

}
