package com.wanda.zuul.gw.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class TurbineServer {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServer.class, args);
    }
}
