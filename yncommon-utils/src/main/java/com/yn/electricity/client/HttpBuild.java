package com.yn.electricity.client;

import org.apache.http.HttpEntity;

/**
 * @author admin
 */
public interface HttpBuild<T> {
    /**
     * 设置编码
     * @param entity
     * @return
     * @throws Exception
     */
    T invok(HttpEntity entity) throws Exception;

}
