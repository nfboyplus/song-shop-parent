package com.song;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * created on 2019/3/3 10:43
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class MemberServer {

    public static void main(String[] args){
        SpringApplication.run(MemberServer.class, args);
    }
}
