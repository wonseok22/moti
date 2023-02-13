package com.main.user.model.service;

public interface RedisUtil {
	String getData(String key);
	
	void setDataExpire(String key, String value, long duration);
}
