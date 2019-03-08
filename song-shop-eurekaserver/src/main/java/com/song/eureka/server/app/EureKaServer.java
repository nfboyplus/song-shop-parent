package com.song.eureka.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * created on 2019/3/3 10:01
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 * 服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class EureKaServer {
    public static void main(String[] args){
        SpringApplication.run(EureKaServer.class, args);
    }
}
