package com.song.common.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * created on 2019/3/9 10:58
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Component
public class TokenUtils {

    public String getToken(){
        return UUID.randomUUID().toString();
    }

}
