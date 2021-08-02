package com.yn.electricity.exception;


/**
 * @ClassName: PasException
 * @Author: zzs
 * @Date: 2021/1/14 17:38
 * @Description:
 */
public class PasException extends RuntimeException{
    public PasException() {
        super();
    }

    public PasException(String message) {
        super(message);
    }

    public PasException(Throwable cause) {
        super(cause);
    }

    public PasException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
