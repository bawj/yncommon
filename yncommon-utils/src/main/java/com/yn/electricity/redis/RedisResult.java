package com.yn.electricity.redis;

import java.io.Serializable;

/**
 * @ClassName: RedisResult
 * @Author: zzs
 * @Date: 2021/6/2 11:14
 * @Description: 查询redis返回对象
 */
public class RedisResult implements Serializable {
    private static final long serialVersionUID = -6007908174709158659L;

    /**
     * 是否成功
     */
    private boolean success = false;
    /**
     * 数据返回
     */
    private Object data;

    public RedisResult setSuccess(boolean success){
        this.success = success;
        return this;
    }

    public RedisResult setData(Object data){
        this.data = data;
        return this;
    }


    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }


}
