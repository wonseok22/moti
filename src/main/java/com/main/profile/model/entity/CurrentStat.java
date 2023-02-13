package com.main.profile.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentStat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long currentStatId;
	private String userId;
	
	private int feedCnt;
	private int relationCnt;
	
	private int healthCnt;
	private int happyCnt;
	
	private int myLikeCnt;
	private int otherLikeCnt;
	
	private int replayCnt;
	private int replayCompleteCnt;
	
	private int commentCnt;
	private int p1_cnt;
	private int p2_cnt;
	private int p3_cnt;
	private int p4_cnt;
	private int p5_cnt;
	private int p6_cnt;
	
	private int lastdayCompleteCnt;
	private int fifthdayCompleteCnt;
	private int followerCnt;
	private int followingCnt;
}
