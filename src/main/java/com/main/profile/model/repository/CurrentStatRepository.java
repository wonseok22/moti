package com.main.profile.model.repository;

import com.main.profile.model.entity.CurrentStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStatRepository extends JpaRepository<CurrentStat, Long> {
	CurrentStat findByUserId(String userId);
}