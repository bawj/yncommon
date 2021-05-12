package com.yn.electricity.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * Date 2021/3/22 16:47
 * Description
 **/
public class ListUtil {

    public static <T> List<T> newArrayList(List<T> list){
        if (CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

}
