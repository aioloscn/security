package com.aiolos.demo.dto;

import com.aiolos.demo.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author Aiolos
 * @date 2019-04-29 14:06
 */
public class User {

    public interface UserSimpleView {};

    public interface UserDetailView extends UserSimpleView {};

    private Long id;

    @MyConstraint(message = "测试自定义Validator")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past
    @ApiModelProperty(value = "生日日期")
    private Date birthday;

    public User() {}

    public User(Long id, String username, String password, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
