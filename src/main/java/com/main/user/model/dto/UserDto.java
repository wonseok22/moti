package com.main.user.model.dto;

import com.main.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userNo;
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private String type;
    private int temporary;
    private String joinDate;

    public User toEntity() {
        return new User(userId, password, email, nickname, null, type,null,  temporary, joinDate, null,null);
    }
}
