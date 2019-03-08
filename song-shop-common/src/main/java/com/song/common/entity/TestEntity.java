package com.song.common.entity;

import lombok.Data;

/**
 * created on 2019/3/7 19:27
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Data
public class TestEntity extends BaseEntity{

    private String userName;
    private String password;
    private String phone;
    private String email;

}
