package org.romanov.yurt.post.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Clob;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "post")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private Long id;
    private Integer postTypeId;
    private Long acceptedAnswerId;
    private LocalDate creationDate;
    private Long score;
    private Long viewCount;
    private Clob body;
    private Long ownerUserId;
    private LocalDate lastActivityDate;
    private String title;
    private String tags;
    private Long answerCount;
    private Long commentCount;
    private Long favoriteCount;
    private String contentLicense;
}
