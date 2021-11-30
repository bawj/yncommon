package com.yn.electricity.enums;

/**
 * 异常消息处理
 */
public enum ResultEnum {

    //成功
    SUCCESS(0, "SUCCESS", "请求处理成功"),
    //失败
    FAIL(1, "FAIL", "请求处理失败"),
    NOT_LOGIN(403 , "FAIL","token错误"),
    ;

    private int code;
    private String msg;
    private String chineseMsg;


    ResultEnum(int code, String msg, String chineseMsg) {
        this.code = code;
        this.msg = msg;
        this.chineseMsg = chineseMsg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getChineseMsg() {
        return chineseMsg;
    }
}
