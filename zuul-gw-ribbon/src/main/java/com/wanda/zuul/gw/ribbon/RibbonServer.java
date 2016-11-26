package com.wanda.zuul.gw.ribbon;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author dan
 * @Since 2016-11-25 21:53
 */
@SpringBootApplication
//@RibbonClients(defaultConfiguration=MyDefaultRibbonConfig.class)  //设置的配置，就不会去加载默认的配置信息
@RibbonClients
@RestController
public class RibbonServer {

    @Autowired
    private SpringClientFactory clientFactory;

    @RequestMapping("/")
    public void getServerList() throws Exception {
        ZoneAwareLoadBalancer<Server> lb = (ZoneAwareLoadBalancer<Server>) clientFactory.getLoadBalancer("zuul-gw-ribbon");
        ServerList<Server> serverList = lb.getServerListImpl();


        List<Server> serverDetailList = serverList.getInitialListOfServers();
        if (!CollectionUtils.isEmpty(serverDetailList)) {
            for (Server s : serverDetailList) {
                System.out.println(s.getHost() + "," + s.getPort());
            }
        } else {
            System.out.println("no service");
        }


    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonServer.class, args);
    }
}
