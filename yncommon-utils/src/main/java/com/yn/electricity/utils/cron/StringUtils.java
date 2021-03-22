package com.yn.electricity.utils.cron;

/**
 * @ClassName: StringUtils
 * @Author: zzs
 * @Date: 2021/1/13 11:18
 * @Description: 字符串工具类
 */
public class StringUtils {

    /**
     * 字符串等于空 返回true 否则返回false
     * @param obj
     * @return
     */
    public static boolean isEmpty(String obj){
        if(null == obj || obj.length() < 1){
            return true;
        }
        return false;
    }

    /**
     * 字符串不等于空返回true 否则返回false
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(String obj){
        if(null != obj && obj.length() > 0){
            return true;
        }
        return false;
    }

    /**
     * 当所有字符串不等于空返回true 否则返回false
     * @param obj
     * @return
     */
    public static boolean isAllNotEmpty(String... obj){
        if(null == obj){
            return false;
        }

        for (int i = 0; i < obj.length; i++) {
            if(isEmpty(obj[i])){
                return false;
            }
        }

        return true;
    }


}
