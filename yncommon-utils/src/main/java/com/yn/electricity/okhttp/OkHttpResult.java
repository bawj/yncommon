package com.yn.electricity.okhttp;

import lombok.Data;

/**
 * @author 返回内容
 * @param <T> 返回值
 */
@Data
public class OkHttpResult <T> {

    /**
     * 错误码 0 请求成功.
     */
    private Integer code;

    /**
     * 提示信息 .
     */
    private String msg;

    /**
     * 只有获取令牌时 返回
     */
    private T token;

    /**
     * 返回的具体内容 .
     */
    private T backResult;
}
