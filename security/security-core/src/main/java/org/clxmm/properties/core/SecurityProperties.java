package org.clxmm.properties.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author clx
 * @date 2020-06-26 21:41
 */
@ConfigurationProperties(prefix = "clx.security")
@Data
public class SecurityProperties {


    private BrowserProperties browser = new BrowserProperties();

    ValidateCodeProperties code = new ValidateCodeProperties();


    SocialProperties social = new SocialProperties();









}
