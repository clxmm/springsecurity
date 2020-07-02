package org.clxmm.properties.core;

import lombok.Data;

/**
 * @author clx
 * @date 2020-06-28 20:06
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }


    private int width = 67;

    private int height = 23;

    private String url;

}
