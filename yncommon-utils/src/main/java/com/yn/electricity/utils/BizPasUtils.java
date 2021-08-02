package com.yn.electricity.utils;

import com.yn.electricity.exception.BusinessException;
import com.yn.electricity.exception.PasException;

/**
 * @ClassName: BizPasUtils
 * @Author: zzs
 * @Date: 2021/1/20 14:14
 * @Description:
 */
public class BizPasUtils {

    public BizPasUtils() {
    }

    public static void bizPasException(String message, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new PasException(message);
    }

    public static void bizPasException(Throwable cause) {
        throw new BusinessException(cause);
    }

    public static void bizPasException(String message, Throwable cause, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new PasException(message, cause);
    }

    public static void bizPasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new PasException(message, cause, enableSuppression, writableStackTrace);
    }

}
