package org.clxmm.properties.core;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author clx
 * @date 2020-07-04 19:25
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";


    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
