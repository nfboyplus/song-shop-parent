package com.song.messages.mq.distribute;

import com.alibaba.fastjson.JSONObject;
import com.song.common.constants.MQInterfaceType;
import com.song.messages.adapter.MessageAdapter;
import com.song.messages.service.SMSMailboxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 功能描述:(消费消息 MQ 从队列获取最新消息)
 * created on 2019/3/8 16:31
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@Component
public class ConsumerDistribute {

    @Autowired
    private SMSMailboxService smsMailboxService;

    @JmsListener(destination = "mail-queue")
    public void distribute(String message) {
        log.info("---消息服务收到消息，消息内容 message:{}", message);
        if (StringUtils.isEmpty(message)) {
            return;
        }
        JSONObject jsonObject = new JSONObject().parseObject(message);
        JSONObject header = jsonObject.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");
        MessageAdapter messageAdapter = null;
        switch (interfaceType) {
            //发送邮件
            case MQInterfaceType.SMS_MAIL:
                messageAdapter = smsMailboxService;
                break;
            default:
                break;
        }
        JSONObject content = jsonObject.getJSONObject("content");
        messageAdapter.distribute(content);
    }
}
