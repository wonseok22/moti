package com.main.flower.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
public class Flower {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flowerId;
	
	private String flowerImageUrl;
}
