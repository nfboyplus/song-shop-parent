package com.song.manage.impl;

import com.alibaba.fastjson.JSONObject;
import com.song.common.api.BaseApiService;
import com.song.common.constants.Constants;
import com.song.common.constants.DBTableName;
import com.song.common.constants.MQInterfaceType;
import com.song.common.enums.MsgCode;
import com.song.common.redis.BaseRedisService;
import com.song.common.utils.DateUtils;
import com.song.common.utils.MD5Util;
import com.song.common.utils.TokenUtils;
import com.song.dao.UserDao;
import com.song.entity.UserEntity;
import com.song.manage.UserServiceManage;
import com.song.mq.producer.RegisterMailboxProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;
import java.util.Map;

/**
 * created on 2019/3/8 13:51
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@Service
public class UserServiceManageImpl extends BaseApiService implements UserServiceManage {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private BaseRedisService baseRedisService;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;

    @Value("${messages.queue}")
    private String MESSAGES_QUEUE;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
     *
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

    /**
     * 登录查找并生成 Token
     *
     * @param userEntity
     * @return
     */
    @Override
    public Map<String, Object> login(UserEntity userEntity) {
        //往数据库进行查找数据
        String phone = userEntity.getPhone();
        String password = userEntity.getPassword();
        String newPassword = md5PassSalt(phone, password);
        UserEntity userPhoneAndPwd = userDao.getUserPhoneAndPwd(phone, newPassword);
        if (userPhoneAndPwd == null) {
            return setResultError(MsgCode.SYS_ACCOUNT_ERROR.getMessage());
        }
        //生成对应的 token
        String token = tokenUtils.getToken();
        Long userId = userPhoneAndPwd.getId();
        //key为自定义令牌,用户的userId作作为value 存放在redis中
        baseRedisService.set(token, userId + "", Constants.USER_TOKEN_TERM_VALIDITY);
        return setResultSuccessData(token);
    }

    @Override
    public Map<String, Object> getUser(String token) {
        //从redis中查找userId
        String userId = baseRedisService.get(token);
        if (StringUtils.isEmpty(userId)){
            return setResultError(MsgCode.SYS_INVALID_LOGIN.getMessage());
        }
        Long newUserId = Long.parseLong(userId);
        UserEntity userEntity = userDao.getUserInfo(newUserId);
        userEntity.setPassword(null);
        return setResultSuccessData(userEntity);
    }

    @Override
    public UserEntity getUserInfo(String phone, String email) {
        return userDao.getUser(phone, email);
    }

}
