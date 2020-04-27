package com.hitrady.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String gmtCreate;
    private String gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
