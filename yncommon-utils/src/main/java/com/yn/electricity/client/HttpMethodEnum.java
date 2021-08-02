package com.yn.electricity.client;



public enum HttpMethodEnum {
    POST("POST"),
    GET("GET");
    private String keys;

    HttpMethodEnum(String keys) {
        this.keys = keys;
    }

    public String getKeys() {
        return keys;
    }
}
