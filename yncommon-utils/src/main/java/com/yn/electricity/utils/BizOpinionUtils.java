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

}
