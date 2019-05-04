package com.aiolos.demo.exception;

import lombok.Data;

/**
 * @author Aiolos
 * @date 2019-05-02 12:12
 */
@Data
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }
}
