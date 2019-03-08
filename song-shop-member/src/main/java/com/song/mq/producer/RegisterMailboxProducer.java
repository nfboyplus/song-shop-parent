package com.song.mq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 功能描述:(往消息服务 推送 邮件信息)
 * created on 2019/3/8 15:16
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate ;

    public void send(Destination destination, String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

}
