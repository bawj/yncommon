package com.yn.electricity.utils;

import org.slf4j.helpers.MessageFormatter;

/**
 * @ClassName: SpliceUtils
 * @Author: zzs
 * @Date: 2021/3/8 9:25
 * @Description:
 */
public class SpliceUtils {

    /**
     * 打印信息拼接
     * @param message
     * @param msg
     * @return
     */
    public static String arrayFormat(String message, String... msg){
        return getString(message, msg);
    }

    public static String getString(String message, String[] msg) {
        if(null != msg && msg.length > 0){
            Object[] obj = new Object[msg.length];
            for (int i = 0; i < msg.length; i++) {
                obj[i] = msg[i];
            }
            message = MessageFormatter.arrayFormat(message, obj).getMessage();
        }
        return message;
    }
}
