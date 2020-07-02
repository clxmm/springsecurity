package org.clxmm.properties.core;

import lombok.Data;

/**
 * @author clx
 * @date 2020-06-30 20:27
 */
@Data
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;

    private String url;
}
