package com.song.api.service.Impl;

import com.song.api.service.UserService;
import com.song.common.enums.MsgCode;
import com.song.manage.UserServiceManage;
import com.song.common.api.BaseApiService;
import com.song.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
            return setResultParameterError(MsgCode.SYS_USER_NAME_NOT_NULL.getMessage());
        }
        if (StringUtils.isEmpty(userEntity.getPassword())){
            return setResultParameterError(MsgCode.SYS_PASSWORD_NOT_NULL.getMessage());
        }
        UserEntity user = userServiceManage.getUserInfo(userEntity.getPhone(), userEntity.getEmail());
        if (null != user){
            return setResultError(MsgCode.SYS_USER_IS_EXIT.getMessage());
        }
        try{
            userServiceManage.register(userEntity);
            return setResultSuccess();
        }catch (Exception e){
            log.error("--- register() ERROR:", e);
            return setResultError(MsgCode.SYS_REGISTER_FAIL.getMessage());
        }
    }

    @Override
    public Map<String, Object> login(@RequestBody UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity.getPhone())){
            return setResultParameterError(MsgCode.SYS_PHONE_NOT_NULL.getMessage());
        }
        if (StringUtils.isEmpty(userEntity.getPassword())){
            return setResultParameterError(MsgCode.SYS_PASSWORD_NOT_NULL.getMessage());
        }
        return userServiceManage.login(userEntity);
    }

    @Override
    public Map<String, Object> getUser(@RequestParam("token") String token) {
        if (StringUtils.isEmpty(token)){
            return setResultParameterError(MsgCode.SYS_TOKEN_NOT_NULL.getMessage());
        }
        return userServiceManage.getUser(token);
    }


}
