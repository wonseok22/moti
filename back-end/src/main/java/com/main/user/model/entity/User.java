package com.main.user.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


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
