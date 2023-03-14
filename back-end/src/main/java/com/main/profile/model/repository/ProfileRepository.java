package com.main.profile.model.repository;

import com.main.profile.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	int deleteByProfileId(Long profileId);
}