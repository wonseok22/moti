package com.main.user.model.repository;

import com.main.user.model.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByEmail(String email);
    User findByNickname(String nickname);
    int deleteByUserId(String userId);
}