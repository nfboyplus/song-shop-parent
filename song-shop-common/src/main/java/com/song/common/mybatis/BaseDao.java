package com.song.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * created on 2019/3/7 19:19
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
public interface BaseDao {

    /**
     * @InsertProvider 注解作用， 自定义sql语句.
     */
    @InsertProvider(type = BaseProvider.class,method="save")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 添加该行，product中的id将被自动添加
    public void save(@Param("object")Object object, @Param("table")String table);

    @InsertProvider(type = BaseProvider.class, method = "update")
    public void update(@Param("object") Object object, @Param("table") String table, @Param("id") Long id);

}
