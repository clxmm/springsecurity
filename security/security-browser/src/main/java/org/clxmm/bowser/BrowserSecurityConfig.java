package org.clxmm.bowser;

import org.clxmm.authentication.ImoocAuthenctiationFailureHandler;
import org.clxmm.authentication.ImoocAuthenticationSuccessHandler;
import org.clxmm.properties.core.SecurityProperties;
import org.clxmm.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author clx
 * @date 2020-06-26 15:50
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    ImoocAuthenctiationFailureHandler imoocAuthenctiationFailureHandler;

    @Autowired
    ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用默认的表单登录
       /* http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();*/


        ValidateCodeFilter filter = new ValidateCodeFilter();
        filter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);


        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .
                //添加自己的过滤器
                        formLogin()
//                .loginPage("/login_c.html")
                .loginPage("/authentication/require")
                //自定义登录的post请求  默认login  post
                .loginProcessingUrl("/authentication/form")
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenctiationFailureHandler)
                .and()
                .authorizeRequests()
                //放行
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(".html");
    }
}
