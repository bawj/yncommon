package com.yn.electricity.logutil;

import com.yn.electricity.utils.SpliceUtils;
import org.slf4j.Logger;

/**
 * @ClassName: YNLogUtils
 * @Author: zzs
 * @Date: 2021/1/19 14:46
 * @Description: 打印日志工具类
 */
public class YNLogUtils {

    public static void error(Logger logger, Throwable e, String msg, String... message){
        SpliceUtils.arrayFormat(msg, message);
        logger.error(msg, e);
    }

}
