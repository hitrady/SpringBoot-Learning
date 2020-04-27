package com.hitrady.community.dto;

import lombok.Data;

/**
 * @author dingweiqiang
 * @date 2020-04-27
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
