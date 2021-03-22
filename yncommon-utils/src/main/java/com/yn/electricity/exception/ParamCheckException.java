package com.yn.electricity.exception;

/**
 * @ClassName: ParamCheckException
 * @Author: zzs
 * @Date: 2021/2/23 15:07
 * @Description:
 */
public class ParamCheckException  extends RuntimeException{

    public ParamCheckException() {
        super();
    }

    public ParamCheckException(String message) {
        super(message);
    }

    public ParamCheckException(Throwable cause) {
        super(cause);
    }

    public ParamCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
