package com.song.api.service;

import com.song.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 描述：登录成功后，生成对应的 Token，作为 Key，将用户 userId 作为 value 存放在 redis 中，返回 Token 给客户端
     * 登陆功能
     *
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserEntity userEntity);

    /**
     * 使用 Token 查找用户信息
     *
     * @param token
     * @return
     */
    @PostMapping("/getUser")
    public Map<String, Object> getUser(@RequestParam("token") String token);

}
