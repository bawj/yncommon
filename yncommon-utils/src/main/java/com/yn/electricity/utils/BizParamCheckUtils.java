package com.yn.electricity.utils;

import com.yn.electricity.exception.ParamCheckException;

/**
 * @ClassName: BizBusinessException
 * @Author: zzs
 * @Date: 2021/1/20 14:14
 * @Description:
 */
public class BizParamCheckUtils {

    public BizParamCheckUtils() {
    }

    public static void BizParamCheckException(String message, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new ParamCheckException(message);
    }

    public static void BizParamCheckException(Throwable cause) {
        throw new ParamCheckException(cause);
    }

    public static void BizParamCheckException(String message, Throwable cause, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new ParamCheckException(message, cause);
    }

    public static void BizParamCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new ParamCheckException(message, cause, enableSuppression, writableStackTrace);
    }



}
