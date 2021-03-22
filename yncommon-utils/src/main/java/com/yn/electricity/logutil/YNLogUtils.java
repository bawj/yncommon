package com.yn.electricity.logutil;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

/**
 * @ClassName: YNLogUtils
 * @Author: zzs
 * @Date: 2021/1/19 14:46
 * @Description: 打印日志工具类
 */
public class YNLogUtils {

    public static void error(Logger logger, Throwable e, String msg, String... message){
        if(null != message && message.length > 0){
            Object[] obj = new Object[message.length];
            for (int i = 0; i < message.length; i++) {
                obj[i] = message[i];
            }
            msg = MessageFormatter.arrayFormat(msg, obj).getMessage();
        }

        logger.error(msg, e);
    }

}
