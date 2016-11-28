package com.wanda.zuul.gw.webapp;

import com.netflix.config.ConfigurationManager;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dan
 * @Since 2016-11-25 18:45
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaServer
@EnableHystrixDashboard
@Slf4j
public class ZuulServer {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServer.class, args);

    }

    @Component
    public static class ZuulCommandLineRunner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            MonitoringHelper.initMocks();
            initJavaFilters();
            initGroovyFilters();

        }

        private void initGroovyFilters() {

        }

        private void initJavaFilters() {
            final FilterRegistry registry = FilterRegistry.instance();
            registry.put("demo", new ZuulFilter() {
                @Override
                public String filterType() {
                    return "pre";
                }

                @Override
                public int filterOrder() {
                    return 0;
                }

                @Override
                public boolean shouldFilter() {
                    return true;
                }

                @Override
                public Object run() {
                    HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
                    System.out.println("fuck");
                    return null;
                }
            });

        }


    }

    private void initZuul() throws Exception {
        log.info("Starting Groovy Filter file manager");
        final AbstractConfiguration config = ConfigurationManager.getConfigInstance();

        FilterLoader.getInstance().setCompiler(new GroovyCompiler());
        FilterFileManager.setFilenameFilter(new GroovyFileFilter());

        final String preFiltersPath ="classpath:filters/pre";
        final String postFiltersPath ="classpath:filters/post";
        final String routingFiltersPath ="classpath:filters/route";

        FilterFileManager.init(5, preFiltersPath, postFiltersPath, routingFiltersPath);
        log.info("Groovy Filter file manager started");
    }

    private void initPlugins() {
    }

}
