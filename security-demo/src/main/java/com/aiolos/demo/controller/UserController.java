package com.aiolos.demo.controller;

import com.aiolos.demo.dto.User;
import com.aiolos.demo.dto.bo.UserQueryConditionBO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aiolos
 * @date 2019-04-29 14:04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryConditionBO user) {

        log.info("user -> {}", ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @GetMapping("{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {

        User user = new User();
        user.setUsername("aiolos");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            List<String> list = errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).distinct().collect(Collectors.toList());
            log.error("errors -> {}", list);
            return user;
        }

        log.info("user -> {}", ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        user.setId(1L);
        return user;
    }

}
