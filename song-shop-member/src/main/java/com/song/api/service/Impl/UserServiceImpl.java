package com.song.api.service.Impl;

import com.song.api.service.UserService;
import com.song.manage.UserServiceManage;
import com.song.common.api.BaseApiService;
import com.song.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * created on 2019/3/8 13:44
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService {

    @Autowired
    private UserServiceManage userServiceManage;

    @Override
    public Map<String, Object> register(@RequestBody UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity.getUserName())){
            return setResultParameterError("用户名称不能为空!");
        }
        if (StringUtils.isEmpty(userEntity.getPassword())){
            return setResultParameterError("密码不能为空!");
        }
        try{
            userServiceManage.register(userEntity);
            return setResultSuccess();
        }catch (Exception e){
            log.error("---- register() ERROR: -----", e);
            return setResultError("注册失败!");
        }
    }

}
