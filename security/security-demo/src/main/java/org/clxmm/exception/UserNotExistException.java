package org.clxmm.exception;

import lombok.Data;

/**
 * @author clx
 * @date 2020-06-24 21:12
 */
@Data
public class UserNotExistException extends RuntimeException {


    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }


}
