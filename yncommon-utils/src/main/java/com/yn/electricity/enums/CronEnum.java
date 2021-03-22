package com.yn.electricity.enums;

public enum CronEnum {
    SECOND("SECOND", "SECOND", "秒", "秒"),
    MINUTE("MINUTE", "MINUTE", "分", "分"),
    HOUR("HOUR", "HOUR", "小时", "小时"),
    DAY("DAY", "DAY", "日", "日"),
    HOUR_MINUTE("HOUR_MINUTE", "HOUR_MINUTE", "小时分钟", "小时分钟"),
    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    CronEnum(String code, String englishName, String chineseName, String msg) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.msg = msg;
    }

    public CronEnum getByCode(String code){
        for (CronEnum value : CronEnum.values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getMsg() {
        return msg;
    }

}
