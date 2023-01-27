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
public class UserDto {
    
    private String userId;
    
    private String password;
    
    private String email;
    
    private String nickname;
    
    private String type;
    
    private int temporary;
    
    private String joinDate;
    
    // 아이고~~
    public static UserDto toDto (User user) {
        return null;
    }
    
    public User toEntity() {
        return new User(userId, password, email, nickname, null, type, null,  temporary, joinDate, null, null, null, null, null);
    }
    
}
