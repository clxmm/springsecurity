package org.clxmm.properties.core;

import lombok.Data;

/**
 * @author clx
 * @date 2020-07-04 19:26
 */
@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();


}
