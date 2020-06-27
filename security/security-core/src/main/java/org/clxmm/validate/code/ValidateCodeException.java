package org.clxmm.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author clx
 * @date 2020-06-27 19:08
 */
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg) {
        super(msg);
    }
}
