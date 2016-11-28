package com.wanda.zuul.gw.webapp;

import com.netflix.config.ConfigurationManager;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;
import com.wanda.zuul.gw.webapp.filters.pre.LoggerFilter;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author dan
 * @Since 2016-11-25 18:45
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@Slf4j
public class ZuulServer {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServer.class, args);

    }

    @Bean
    public LoggerFilter loggerFilter() {
        return new LoggerFilter();
    }

    @Component
    public static class ZuulCommandLineRunner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            MonitoringHelper.initMocks();
            initGroovyFilters();

        }

        private void initGroovyFilters() {

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
