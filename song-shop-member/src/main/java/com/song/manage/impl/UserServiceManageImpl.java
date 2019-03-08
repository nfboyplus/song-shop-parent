package com.song.manage.impl;

import com.alibaba.fastjson.JSONObject;
import com.song.common.constants.MQInterfaceType;
import com.song.dao.UserDao;
import com.song.manage.UserServiceManage;
import com.song.common.constants.DBTableName;
import com.song.common.utils.DateUtils;
import com.song.common.utils.MD5Util;
import com.song.entity.UserEntity;
import com.song.mq.producer.RegisterMailboxProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * created on 2019/3/8 13:51
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@Service
public class UserServiceManageImpl implements UserServiceManage {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;

    @Value("${messages.queue}")
    private String MESSAGES_QUEUE;

    @Override
    public void register(UserEntity userEntity) {
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        String phone = userEntity.getPhone();
        String password = userEntity.getPassword();
        userEntity.setPassword(md5PassSalt(phone, password));
        userDao.save(userEntity, DBTableName.TABLE_MB_USER);
        //队列
        Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
        //JSON 组装
        String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());
        log.info("----- register() 注册发送邮件报文 mailMessage:{}", mailMessage);
        registerMailboxProducer.send(activeMQQueue, mailMessage);
    }

    /**
     * 组装报文
     * @param email
     * @param userName
     * @return
     */
    private String mailMessage(String email, String userName) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", MQInterfaceType.SMS_MAIL);
        JSONObject content = new JSONObject();
        content.put("mail", email);
        content.put("userName", userName);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }

    @Override
    public String md5PassSalt(String phone, String password) {
        String newPassword = MD5Util.MD5(phone + password);
        return newPassword;
    }

}
