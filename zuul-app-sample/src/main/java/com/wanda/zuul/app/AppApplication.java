package com.wanda.zuul.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.Properties;

/**
 * @Author dan
 * @Since 2016-11-26 09:50
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class AppApplication {

    public static void main(String[] args) {
        System.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", "10000");

        SpringApplication.run(AppApplication.class, args);
    }

}
