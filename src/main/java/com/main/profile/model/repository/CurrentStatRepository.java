package com.main.profile.model.repository;

import com.main.profile.model.entity.CurrentStat;
import com.main.profile.model.entity.Profile;
import org.omg.CORBA.Current;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStatRepository extends JpaRepository<CurrentStat, Long> {
	CurrentStat findByUser_UserId(String userId);
}