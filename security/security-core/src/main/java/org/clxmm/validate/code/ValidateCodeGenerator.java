package org.clxmm.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author clx
 * @date 2020-06-28 21:12
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
