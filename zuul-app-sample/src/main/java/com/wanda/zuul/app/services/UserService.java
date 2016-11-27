package com.wanda.zuul.app.services;

import com.wanda.zuul.app.dto.User;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author dan
 * @Since 2016-11-27 10:42
 */
@FeignClient("zuul-service-sample")
public interface UserService {

    @RequestMapping(
            value = "/users/{userId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    User getUserById(
            @PathVariable("userId") int userId
    );
}
