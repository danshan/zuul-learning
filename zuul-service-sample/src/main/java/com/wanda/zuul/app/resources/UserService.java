package com.wanda.zuul.app.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wanda.zuul.app.dto.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dan
 * @Since 2016-11-26 10:06
 */
@RestController
public class UserService {

    @RequestMapping(
            value = "/users/{userId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public User getUserById(
            @PathVariable("userId") int userId
    ) {
        System.out.println("receive request");
        if (System.currentTimeMillis() % 3 == 0) {
            throw new IllegalStateException("auto failed");
        }

        User user = new User();
        user.setUserId(userId);
        user.setUsername("fuckgfw");

        return user;
    }

    public User getDefaultUser(
            int userId
    ) {
        User user = new User();
        user.setUserId(-1);
        user.setUsername("default user");
        return user;
    }

}
