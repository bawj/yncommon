package com.yn.electricity.exception;


/**
 * @ClassName: PasException
 * @Author: zzs
 * @Date: 2021/1/14 17:38
 * @Description:
 */
public class PasException extends RuntimeException{

    private int code;

    public PasException() {
        super();
    }

    public PasException(String message) {
        super(message);
        this.code = code;
    }

    public PasException(Throwable cause) {
        super(cause);
        this.code = code;
    }

    public PasException(String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public PasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
