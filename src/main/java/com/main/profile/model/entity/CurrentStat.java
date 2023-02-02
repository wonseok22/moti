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
	
	private int feed_cnt;
	private int relation_cnt;
	
	private int health_cnt;
	private int happy_cnt;
	
	private int my_like_cnt;
	private int other_like_cnt;
	
	private int replay_cnt;
	private int replay_complete_cnt;
	
	private int comment_cnt;
	private int p1_cnt;
	private int p2_cnt;
	private int p3_cnt;
	private int p4_cnt;
	private int p5_cnt;
	private int p6_cnt;
	
	private int lastday_complete_cnt;
	private int fifthday_complete_cnt;
	private int follower_cnt;
	private int following_cnt;
	
	
}
