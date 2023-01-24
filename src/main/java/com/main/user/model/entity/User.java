package com.main.user.model.entity;


<<<<<<< HEAD:src/main/java/com/main/user/model/entity/User.java
import com.main.profile.model.entity.Profile;
import lombok.*;
import org.springframework.boot.context.properties.bind.Name;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> 8371f0215ece89199d2a6043f4907775d4e66149:back-end/src/main/java/com/main/user/model/entity/User.java

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

<<<<<<< HEAD:src/main/java/com/main/user/model/entity/User.java
    @OneToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

=======
>>>>>>> 8371f0215ece89199d2a6043f4907775d4e66149:back-end/src/main/java/com/main/user/model/entity/User.java
}
