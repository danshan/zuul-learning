package com.wanda.zuul.gw.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author dan
 * @Since 2016-11-25 18:45
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulServer {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServer.class, args);
    }
}
