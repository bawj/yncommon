package com.yn.electricity.okhttp;

import lombok.Data;

@Data
public class OauthParam {

    /**
     * 用户名 接入方
     */
    private String user;
    /**
     * 编号 BASE64 的值
     */
    private String deviceCode;

    /**
     * 访问令牌
     */
    private String token;

}
