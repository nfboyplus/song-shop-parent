package com.song.common.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * created on 2019/3/5 19:44
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Data
public class BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Timestamp created;

    /**
     * 修改时间
     */
    private Timestamp updated;

}
