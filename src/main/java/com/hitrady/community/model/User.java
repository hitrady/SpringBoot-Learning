package com.hitrady.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private String gmtCreate;
    private String gmtModified;
}
