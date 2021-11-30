package com.yn.electricity.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2020/5/28 0028 09:49
 **/
@Data
public class ResultVO<T>  implements Serializable {

    private static final long serialVersionUID = 7611423267758084961L;
    /**
     * 错误码 0 请求成功.
     */
    private Integer code;

    /**
     * 提示信息 .
     */
    private String msg;

    /**
     * 返回的具体内容 .
     */
    private T data;

}
