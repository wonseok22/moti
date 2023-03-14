package com.main.user.model.repository;

import com.main.user.model.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
	
	User findByEmail(String email);
	
	User findByNickname(String nickname);
	
	int deleteByUserId(String userId);
	
	Slice<User> findAllByNicknameLike(String s, PageRequest of);
}