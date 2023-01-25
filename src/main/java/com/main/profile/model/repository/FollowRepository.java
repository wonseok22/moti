package com.main.profile.model.repository;

import com.main.profile.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository {
    List<Follow> findAllByFollowerId(String userId);
    List<Follow> findAllByFollowingId(String userId);
}
