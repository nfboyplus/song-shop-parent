package com.song.api.service;

import com.song.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 用户服务
 * created on 2019/3/8 13:41
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@RequestMapping("/member")
public interface UserService {

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserEntity userEntity);

}
