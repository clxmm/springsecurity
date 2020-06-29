package org.clxmm.properties.core;

import lombok.Data;
import org.clxmm.properties.LoginResponseType;

/**
 * @author clx
 * @date 2020-06-26 21:40
 */
@Data
public class BrowserProperties {

    private String loginPage = "/login_c.html";

    private int rememberMeSeconds = 3600;


    private LoginResponseType loginType = LoginResponseType.JSON;
}
