package com.main.user.model.service;

public interface RedisUtil {
    public String getData(String key);

    public void setData(String key, String value);

    public void setDataExpire(String key, String value, long duration);
}
