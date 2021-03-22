package com.yn.electricity.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateCompareUtil
 * @Author: zzs
 * @Date: 2021/1/18 10:45
 * @Description:
 */
public class DateCompareUtil {

    /**
     * afterTime 时间与 beforeTime 时间相差值，返回秒数
     * @param beforeTime
     * @param afterTime
     * @return
     */
    public static long compareDate(Date beforeTime, Date afterTime){
        if(null == beforeTime || null == afterTime){
            throw new RuntimeException("参数异常");
        }

        Calendar before = Calendar.getInstance();
        before.setTime(beforeTime);
        Calendar after = Calendar.getInstance();
        after.setTime(afterTime);

        long difference = after.getTimeInMillis() - before.getTimeInMillis();
        return difference / 1000 ;
    }

}
