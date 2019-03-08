package com.song.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * created on 2019/3/3 12:13
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Component
public class BaseRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 使用Redis添加信息
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value ) {
        set(key, value, null);
    }

    public void setString(String key, String value, Long timeOut) {
        set(key, value, timeOut);
    }

    public void set(String key, Object value, Long timeOut) {
        if (value != null) {
            if (value instanceof String) {
                String setValue = (String) value;
                stringRedisTemplate.opsForValue().set(key, setValue);
            }
            //设置有效期
            if (timeOut != null) {
                stringRedisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 使用Redis查找信息
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 使用Redis删除信息
     *
     * @param kye
     */
    public void delete(String kye) {
        stringRedisTemplate.delete(kye);
    }
}
