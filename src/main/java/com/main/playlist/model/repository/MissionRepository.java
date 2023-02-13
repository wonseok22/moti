package com.main.playlist.model.repository;

import com.main.playlist.model.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
	Mission findByMissionId(Long missionId);
}
