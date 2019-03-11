package com.song.entity;

import com.song.common.entity.BaseEntity;
import lombok.Data;

/**
 * created on 2019/3/8 13:33
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Data
public class UserEntity extends BaseEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ关联Id
     */
    private String openId;

}
