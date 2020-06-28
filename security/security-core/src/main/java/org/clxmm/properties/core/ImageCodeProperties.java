package org.clxmm.properties.core;

import lombok.Data;

/**
 * @author clx
 * @date 2020-06-28 20:06
 */
@Data
public class ImageCodeProperties {


    private int width = 67;

    private int height = 23;


    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 失效时间
     */
    private int expireIn = 60;

    private String url;

}
