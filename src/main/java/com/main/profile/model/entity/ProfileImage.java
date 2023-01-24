package com.main.profile.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity(name = "profileImage")
public class ProfileImage {
    @Id
    private Long id;
    private String saveFolder;
    private String originalFile;
    private String saveFile;



}
