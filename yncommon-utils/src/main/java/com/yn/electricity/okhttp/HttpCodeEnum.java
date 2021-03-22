package com.yn.electricity.okhttp;

/**
 * @author 错误码
 */
public enum HttpCodeEnum {

    /** 错误码 */
    HTTP_CODE_200(200, "正常返回或执行"),
    HTTP_CODE_201(201, "正在推流"),
    ;

    private int code;
    private String msg;


    HttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
