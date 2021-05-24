package com.yn.electricity.enums;

/**
 * @author zzs
 */

public enum ExecutionResultEnum {
    SUCCESS("200", "SUCCESS", "操作成功", "操作成功"),
    FAIL("201", "FAIL", "操作失败", "操作失败"),

    ;

    private String code;

    private String englishName;

    private String chineseName;

    private String msg;

    ExecutionResultEnum(String code, String englishName, String chineseName, String msg) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.msg = msg;
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
