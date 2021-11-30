package com.yn.electricity.result;


import com.yn.electricity.enums.ResultEnum;

/**
 * @author Administrator
 * @date 2020/5/28 0028 09:48
 * 统一返回封装
 **/
public class ResultUtilVO {

    public static <T> ResultVO<T> onSuccess(T object) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }

    public static <T> ResultVO<T> onSuccess(T object, Integer code, String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

    public static <T> ResultVO<T> onSuccess() {
        return onSuccess(null);
    }

    public static <T> ResultVO<T> onErrorResult(Integer code, String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(null);
        return resultVO;
    }


    public static <T> ResultVO<T> onErrorResult(T t, Integer code, String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(t);
        return resultVO;
    }

    @Override
    public String toString() {
        return "ResultUtilVO{}";
    }

}
