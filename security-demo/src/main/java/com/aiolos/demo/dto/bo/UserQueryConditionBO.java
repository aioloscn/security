package com.aiolos.demo.dto.bo;

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

    private int age;

    private int ageTo;
}
