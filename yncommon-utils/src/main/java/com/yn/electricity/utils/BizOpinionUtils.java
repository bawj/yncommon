package com.yn.electricity.utils;

import com.yn.electricity.utils.cron.StringUtils;

/**
 * @ClassName: BizOpinionUtils
 * @Author: zzs
 * @Date: 2021/2/23 9:29
 * @Description:
 */
public class BizOpinionUtils {

    /**
     * 所有验证参数不能为空
     * @param message
     * @param obj
     */
    public static void isAllNotEmpty(String message, String... obj){
        boolean result = StringUtils.isAllNotEmpty(obj);
        if(!result){
            BizBusinessUtils.bizBusinessException(message, obj);
        }
    }

    /**
     * 字符串不能为空
     * @param obj
     */
    public static void isNotEmpty(String obj){
        boolean result = StringUtils.isNotEmpty(obj);
        if(!result){
            BizBusinessUtils.bizBusinessException("必填参数不能is null obj:{}", obj);
        }
    }

    /**
     * 检查Integer参数不能为空
     * @param message 打印内容
     * @param obj 需要验证的字段
     */
    public static void isNotNull(String message, Integer obj){
        if(null == obj){
            BizBusinessUtils.bizBusinessException(message);
        }
    }

    /**
     * 检查Integer所有参数都不能为空
     * @param message 打印内容
     * @param obj 需要验证的字段
     */
    public static void isAllNotNull(String message, Integer... obj){
        if(null == obj){
            return;
        }

        int emptySize = 0;
        String[] list = new String[obj.length];
        for (int i = 0; i < obj.length; i++) {
            String str = String.valueOf(obj[i]);
            list[i] = StringUtils.isEmpty(str)?"":str;
            if(null == obj[i]){
                emptySize ++ ;
            }
        }
        if(emptySize > 0){
            BizBusinessUtils.bizBusinessException(message, list);
        }
    }

}
