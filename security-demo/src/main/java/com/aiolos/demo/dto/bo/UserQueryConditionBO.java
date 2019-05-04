package com.aiolos.demo.dto.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aiolos
 * @date 2019-04-29 14:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryConditionBO {

    private String username;

    @ApiModelProperty(value = "用户年龄开区间")
    private int age;

    @ApiModelProperty(value = "用户年龄闭区间")
    private int ageTo;
}
