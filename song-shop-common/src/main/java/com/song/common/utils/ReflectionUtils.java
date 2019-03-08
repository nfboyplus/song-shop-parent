package com.song.common.utils;

import com.song.common.entity.TestEntity;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * 功能描述:(反射工具类F)
 * created on 2019/3/7 19:24
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
public class ReflectionUtils {

    /**
     *
     * @methodDesc: 功能描述:(封装当前类和父类的所有属性，拼接属性sql)
     * @param: object
     * @param: @return
     */
    public static String fatherAndSonField(Object object) {
        if (object == null) {
            return null;
        }
        // 获取class文件
        Class classInfo = object.getClass();
        // 获取当前类属性sql
        Field[] sonFields = classInfo.getDeclaredFields();
        String s1 = getField(sonFields);
        // 获取父类的属性
        Field[] parentFields = classInfo.getSuperclass().getDeclaredFields();
        String s2 = getField(parentFields);
        return s1 + "," + s2;
    }

    /**
     *
     * @methodDesc: 功能描述:(获取到属性值)
     * @param: @object
     * @param: @return
     */
    public static String fatherAndSonFieldValue(Object object) {
        if (object == null) {
            return null;
        }
        //获取class文件
        Class classInfo = object.getClass();
        //获取当前类属性sql
        Field[] sonFields = classInfo.getDeclaredFields();
        String s1 = getFieldValue(object, sonFields);
        //获取父类的属性
        Field[] parentFields = classInfo.getSuperclass().getDeclaredFields();
        String s2 = getFieldValue(object, parentFields);
        return s1 + "," + s2;
    }

    public static String getField(Field[] declaredFields) {
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < declaredFields.length; i++) {
            sf.append(declaredFields[i].getName());
            if (i < declaredFields.length - 1) {
                sf.append(",");
            }
        }
        return sf.toString();
    }

    public static String getFieldValue(Object object, Field[] declaredFields) {
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < declaredFields.length; i++) {
            // 获取到属性值
            try {
                Field field = declaredFields[i];
                // 运行操作私有属性
                field.setAccessible(true);
                Object value = field.get(object);
                // 标识类型是否为string类型，如果是String类型加单引号
                boolean flag = false;
                if (value != null && (value instanceof String || value instanceof Timestamp)) {
                    flag = true;
                }
                if (flag) {
                    sf.append("'");
                    sf.append(value);
                    sf.append("'");
                } else {
                    sf.append(value);
                }
                if (i < declaredFields.length - 1) {
                    sf.append(",");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sf.toString();
    }

    public static void main(String[] args) {
        TestEntity testEntity = new TestEntity();
        testEntity.setUserName("张三");
        testEntity.setPhone("15921009245");
        testEntity.setEmail("644064779@qq.com");
        testEntity.setPassword("123456");
        testEntity.setCreated(DateUtils.getTimestamp());
        testEntity.setUpdated(DateUtils.getTimestamp());
        String filed = fatherAndSonField(testEntity);
        String value = fatherAndSonFieldValue(testEntity);
        // 封装sql
        SQL sql = new SQL(){{
            INSERT_INTO("mb_user");
            VALUES(filed, value);
        }};
        System.out.println(sql.toString());

    }


}
