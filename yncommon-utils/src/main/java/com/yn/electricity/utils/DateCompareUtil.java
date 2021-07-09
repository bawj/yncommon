package com.yn.electricity.utils;

import java.time.LocalDate;
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

    /**
     * 检查两个日期是否相等
     * 注： currCal.get(Calendar.MONTH) 获取的月份会少1在获取时故加1
     * @param currDay
     * @param contrastDay
     * @return
     */
    public static boolean checkDayEqual(Date currDay, Date contrastDay){
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(currDay);
        Calendar contrastCal = Calendar.getInstance();
        contrastCal.setTime(contrastDay);

        LocalDate currDate = LocalDate.of(currCal.get(Calendar.YEAR), currCal.get(Calendar.MONTH) +1, currCal.get(Calendar.DAY_OF_MONTH));
        LocalDate contrastDate = LocalDate.of(contrastCal.get(Calendar.YEAR), contrastCal.get(Calendar.MONTH) +1, contrastCal.get(Calendar.DAY_OF_MONTH));
        if (currDate.equals(contrastDate)){
            return true;
        }else {
            return false;
        }
    }

}
