package org.clxmm.social.qq.config;

import org.clxmm.properties.core.QQProperties;
import org.clxmm.properties.core.SecurityProperties;
import org.clxmm.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author clx
 * @date 2020-07-04 19:28
 */
@Configuration
@ConditionalOnProperty(prefix = "clx.security.social.qq",name = "app_id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        System.out.println("clx");
        System.out.println(qqConfig);

        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
