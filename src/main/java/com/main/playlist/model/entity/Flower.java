package com.main.playlist.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
public class Flower {
    @Id
    private Long flowerId;

    private String saveFolder;

    private String originalFile;

    private String saveFile;
}
