package com.song.messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 消息服务
 * created on 2019/3/8 16:26
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class MessagesServer {

    public static void main(String[] args) {
        SpringApplication.run(MessagesServer.class, args);
    }
}
