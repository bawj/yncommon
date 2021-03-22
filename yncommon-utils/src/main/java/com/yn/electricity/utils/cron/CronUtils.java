package com.yn.electricity.utils.cron;


import com.yn.electricity.enums.CronEnum;
import com.yn.electricity.exception.BusinessException;

/**
 * @ClassName: CronUtils
 * @Author: zzs
 * @Date: 2021/1/16 9:20
 * @Description:
 */
public class CronUtils {

    /**
     * 获取cron表达式
     * @param cronEnum
     * @param processTime
     * @return
     */
    public static String getCron(CronEnum cronEnum, String processTime, String... hourTime){
        switch (cronEnum){
            case DAY:
                return generateDayCron("1", processTime);
            case HOUR:
                return generateHourCron("0", processTime);
            case MINUTE:
                return generateMinuteCron("0", processTime);
            case SECOND:
                return generateSecondCron("0", processTime);
            case HOUR_MINUTE:
                return generateHourAndMinuteCron("0", processTime, hourTime);
            default:
                throw new BusinessException("不支持该时间段");
        }
    }

    /**
     * 生成以时间和分钟为单位的cron表达式
     * @param startTime
     * @param processMinute
     * @param processHour
     * @return
     */
    public static String generateHourAndMinuteCron(String startTime, String processMinute, String... processHour){
        StringBuffer cronSecond = new StringBuffer();

        if(null == processHour || processHour.length < 1){
            throw new RuntimeException("执行时间单位小时未传入");
        }
        startTime = verifyParam(startTime, processMinute, processHour[0]);

        Integer start = Integer.valueOf(startTime);
        if(59 < start){
            start = 59;
        }
        Integer minute = Integer.valueOf(processMinute);
        if(59 < minute){
            minute = 59;
        }
        Integer hour = Integer.valueOf(processHour[0]);
        if(12 < hour){
            hour = 1;
        }

        cronSecond.append("0 ").append(minute).append(" ")
                .append(start).append("/").append(hour)
                .append(" *").append(" *").append(" ?");
        return cronSecond.toString();
    }

    /**
     * 生成以秒为单位的cron表达式
     * @param startSecond
     * @param second
     * @return 20/10 * * * * ?
     */
    public static String generateSecondCron(String startSecond, String second){
        StringBuffer cronSecond = new StringBuffer();

        startSecond = CronUtils.verifyParam(startSecond, second);

        Integer start = Integer.valueOf(startSecond);
        if(59 < start){
            start = 59;
        }
        Integer secondProcess = Integer.valueOf(second);
        if(59 < secondProcess){
            secondProcess = 59;
        }


        cronSecond.append(start).append("/").append(secondProcess)
                .append(" *").append(" *").append(" *")
                .append(" *").append(" ?");
        return cronSecond.toString();
    }

    /**
     * 生成以分为单位的cron表达式
     * @param startTime
     * @param processTime
     * @return 0 20/5 * * * ? *
     */
    public static String generateMinuteCron(String startTime, String processTime){
        StringBuffer cronSecond = new StringBuffer();

        startTime = CronUtils.verifyParam(startTime, processTime);



        Integer start = Integer.valueOf(startTime);
        if(59 < start){
            start = 59;
        }
        Integer process = Integer.valueOf(processTime);
        if(59 < process){
            process = 59;
        }

        cronSecond.append("0 ").append(start).append("/").append(process)
                .append(" *").append(" *").append(" *")
                .append(" ?").append(" *");
        return cronSecond.toString();
    }

    /**
     * 生成以小时为单位的cron表达式
     * @param startTime
     * @param processTime
     * @return 0 0 9/1 * * ? *
     * 参数大于12小时按每隔一个小时执行一次
     */
    public static String generateHourCron(String startTime, String processTime){
        StringBuffer cronSecond = new StringBuffer();

        startTime = CronUtils.verifyParam(startTime, processTime);

        Integer start = Integer.valueOf(startTime);
        if(12 < start){
            start = 0;
        }
        Integer process = Integer.valueOf(processTime);
        if(12 < process){
            process = 1;
        }

        cronSecond.append(" 0").append(" 0 ")
                .append(start).append("/").append(process)
                .append(" *").append(" *")
                .append(" ?").append(" *");
        return cronSecond.toString();
    }

    /**
     * 生成以日为单位的cron表达式
     * @param startTime
     * @param processTime
     * @return 0 0 0 20/1 * ?
     * 参数大于31，按每隔31天执行一次
     */
    public static String generateDayCron(String startTime, String processTime){
        StringBuffer cronSecond = new StringBuffer();

        startTime = CronUtils.verifyParam(startTime, processTime);

        Integer start = Integer.valueOf(startTime);
        if(28 < start){
            start = 0;
        }
        Integer process = Integer.valueOf(processTime);
        if(31 < process){
            process = 31;
        }

        cronSecond.append(" 0").append(" 0").append(" 0 ")
                .append(start).append("/").append(process)
                .append(" *").append(" ?");
        return cronSecond.toString();
    }

    /**
     * 验证参数
     * @param startTime
     * @param processTime
     * @return
     */
    private static String verifyParam(String startTime, String processTime){
        if(StringUtils.isEmpty(startTime)){
            startTime = "1";
        }
        if(StringUtils.isEmpty(processTime)){
            throw new BusinessException("执行时间未传入 second:{"+processTime+"}");
        }
        return startTime;
    }

    /**
     * 验证参数
     * @param startTime
     * @param processTime
     * @return
     */
    private static String verifyParam(String startTime, String processTime, String time){
        if(StringUtils.isEmpty(startTime)){
            startTime = "1";
        }
        if(StringUtils.isEmpty(processTime) || StringUtils.isEmpty(time)){
            throw new RuntimeException("执行时间未传入 second:{"+processTime+"}, time:{"+time+"}");
        }
        return startTime;
    }


    public static void main(String[] args) {
        System.out.println(CronUtils.getCron(CronEnum.HOUR_MINUTE, "0", "0"));
    }
}
