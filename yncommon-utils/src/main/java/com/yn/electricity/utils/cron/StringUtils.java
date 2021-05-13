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

    /**
     * 判断两个字符串值是否相等
     * @param var1
     * @param var2
     * @return
     */
    public static boolean compareTo(String var1, String var2){
        if(StringUtils.isEmpty(var1) && StringUtils.isNotEmpty(var2)){
            return false;
        }else if (StringUtils.isNotEmpty(var1) && StringUtils.isEmpty(var2)){
            return false;
        }else if(null == var1 && "".equals(var2)){
            return true;
        }else if(null == var2 && "".equals(var1)){
            return true;
        }else if(null == var1 && null == var2){
            return true;
        }else if (var1.equals(var2)){
            return true;
        }
        return false;
    }

}
