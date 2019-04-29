package com.aiolos.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aiolos
 * @date 2019-04-29 14:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private String password;
}
