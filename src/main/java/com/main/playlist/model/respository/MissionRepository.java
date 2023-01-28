package com.main.playlist.model.respository;

import com.main.playlist.model.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
	public Mission findByMissionId(Long missionId);
}
