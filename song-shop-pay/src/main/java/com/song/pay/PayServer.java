package com.song.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * created on 2019/3/15 14:51
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class PayServer {

    public static void main(String[] args){
        SpringApplication.run(PayServer.class, args);
    }
}
