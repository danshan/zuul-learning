package com.wanda.zuul.app.resources;

import com.netflix.discovery.converters.Auto;
import com.wanda.zuul.app.dto.User;
import com.wanda.zuul.app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author dan
 * @Since 2016-11-26 10:06
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(
            value = "/users/{userId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public User getUserById(
            @PathVariable("userId") int userId
    ) {
        User user = userService.getUserById(userId);
        return user;
    }

}
