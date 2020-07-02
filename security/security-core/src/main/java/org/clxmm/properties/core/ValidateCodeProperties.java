package org.clxmm.properties.core;

import lombok.Data;

/**
 * @author clx
 * @date 2020-06-28 20:11
 */
@Data
public class ValidateCodeProperties {


    private ImageCodeProperties image = new ImageCodeProperties();

    SmsCodeProperties sms = new SmsCodeProperties();



}
