package com.song.manage;

import com.song.entity.UserEntity;

/**
 * created on 2019/3/8 13:49
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
public interface UserServiceManage {

    /**
     * 功能描述:(注册服务)
     *
     * @param userEntity
     */
    public void register(UserEntity userEntity);

    /**
     * 密码加密
     *
     * @param phone
     * @param password
     * @return
     */
    public String md5PassSalt(String phone, String password);

}
