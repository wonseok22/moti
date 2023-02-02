package com.main.profile.model.repository;

import com.main.profile.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
	List<Follow> findAllByFollowerId(String userId);
	
	List<Follow> findAllByFollowingId(String userId);
	
	Follow findByFollowerIdAndFollowingId(String requestId, String targetId);
}
