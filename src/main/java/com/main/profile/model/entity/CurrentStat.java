package com.main.profile.model.entity;

import com.main.user.model.entity.User;
import lombok.*;

import javax.persistence.*;

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
	private int p1Cnt;
	private int p2Cnt;
	private int p3Cnt;
	private int p4Cnt;
	private int p5Cnt;
	private int p6Cnt;
	
	private int lastdayCompleteCnt;
	private int fifthdayCompleteCnt;
	private int followerCnt;
	private int followingCnt;
	
	
}
