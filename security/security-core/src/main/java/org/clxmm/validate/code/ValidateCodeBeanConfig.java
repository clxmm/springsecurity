package org.clxmm.validate.code;

import org.clxmm.properties.core.SecurityProperties;
import org.clxmm.properties.image.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clx
 * @date 2020-06-28 21:21
 */
@Configuration
public class ValidateCodeBeanConfig {


    @Autowired
    private SecurityProperties securityProperties;

    /**
     * ConditionalOnMissingBean  在启动之前查看是否存在imageValidateCodeGenerator  ，不存在才用默认的配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
}
