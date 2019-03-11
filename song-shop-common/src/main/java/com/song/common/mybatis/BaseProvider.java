package com.song.common.mybatis;

import com.song.common.utils.ReflectionUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * created on 2019/3/7 19:21
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
public class BaseProvider {

    /**
     *
     * @methodDesc: 功能描述:(自定义封装sql语句)
     * @param: @return
     */
    public String save(Map<String, Object> map) {
        // 实体类
        Object object = map.get("object");
        // 表名称
        String table = (String) map.get("table");
        // 生成添加的sql语句,使用反射机制
        // 步驟：使用反射机制加载这个类所有属性
        SQL sql = new SQL() {
            {
                INSERT_INTO(table);
                VALUES(ReflectionUtils.fatherAndSonField(object), ReflectionUtils.fatherAndSonFieldValue(object));
            }
        };
        return sql.toString();
    }

    /**
     *
     * @methodDesc: 功能描述:(自定义封装sql语句)
     * @param: @return
     */
    static public String update(Map<String, Object> map) {
        // 实体类
        Object object = map.get("object");
        // 表名称
        String table = (String) map.get("table");
        // id
        Long id = (Long) map.get("id");
        // 生成添加的sql语句。 使用反射机制
        // 步驟：使用反射机制加载这个类所有属性
        SQL sql = new SQL() {
            {

                UPDATE(table);
                SET(ReflectionUtils.fatherAndSonFieldValue(object));
                WHERE(" id= " + id);
            }
        };
        return sql.toString();
    }

}
