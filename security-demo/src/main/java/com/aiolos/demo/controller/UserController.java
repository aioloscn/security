package com.aiolos.demo.controller;

import com.aiolos.demo.dto.User;
import com.aiolos.demo.dto.bo.UserQueryConditionBO;
import com.aiolos.demo.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/me")
    @ApiOperation(value = "获取当前用户信息")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping("/my")
    public Object getMyDetails(Authentication user) {
        return user;
    }

    @GetMapping("/loginData")
    @ApiOperation(value = "获取当前用户信息，包含登录信息")
    public Object getAutenticationDetails(Authentication authentication) {
//        return SecurityContextHolder.getContext().getAuthentication();
        // springmvc会自动去spring security context中找登录信息
        return authentication;
    }

    @GetMapping("/error/{id:\\d+}")
    public void userError(String id) {

        throw new UserNotExistException(id);
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryConditionBO user) {

        log.info("query user -> {}", ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @GetMapping("{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam("用户主键") @PathVariable String id) {

        log.info("getInfo method");

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

    @PutMapping
    public User update(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            List<String> list = errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).distinct().collect(Collectors.toList());
            log.error("errors -> {}", list);
            return user;
        }

        log.info("user -> {}", ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setUsername("aaa");
        return user;
    }

    @DeleteMapping("{id:\\d+}")
    public void delete(@PathVariable String id) {
        log.info("delete id -> {}", id);
    }

}
