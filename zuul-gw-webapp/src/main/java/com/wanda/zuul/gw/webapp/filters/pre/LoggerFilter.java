package com.wanda.zuul.gw.webapp.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.java.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dan
 * @Since 2016-11-28 15:07
 */
@Log
public class LoggerFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        log.info("request:" + request);
        return null;
    }
}
