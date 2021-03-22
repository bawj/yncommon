package com.yn.electricity.utils;

import com.yn.electricity.exception.BusinessException;
import org.slf4j.helpers.MessageFormatter;

/**
 * @ClassName: BizBusinessException
 * @Author: zzs
 * @Date: 2021/1/20 14:14
 * @Description:
 */
public class BizBusinessUtils {

    public BizBusinessUtils() {
    }

    public static void bizBusinessException(String message, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new BusinessException(message);
    }

    public static void bizBusinessException(Throwable cause) {
        throw new BusinessException(cause);
    }

    public static void bizBusinessException(String message, Throwable cause, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new BusinessException(message, cause);
    }

    public static void bizBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String... msg) {
        message = SpliceUtils.arrayFormat(message, msg);
        throw new BusinessException(message, cause, enableSuppression, writableStackTrace);
    }

}
