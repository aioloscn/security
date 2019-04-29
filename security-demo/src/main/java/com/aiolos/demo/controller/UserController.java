package com.aiolos.demo.controller;

import com.aiolos.demo.dto.User;
import com.aiolos.demo.dto.bo.UserQueryConditionBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aiolos
 * @date 2019-04-29 14:04
 */
@RestController
@Slf4j
public class UserController {

    @GetMapping("/user")
    public List<User> query(@RequestBody UserQueryConditionBO user) {

        log.info("user -> {}", user);

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

}
