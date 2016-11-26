package com.wanda.zuul.app.resources;

import com.netflix.discovery.EurekaClient;
import com.wanda.zuul.app.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @RequestMapping(
            value = "/users/{userId}",
            method = RequestMethod.GET
    )
    public @ResponseBody User getUserById(
            @PathVariable("userId") int userId
    ) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("fuckgfw");

        return user;
    }

}
