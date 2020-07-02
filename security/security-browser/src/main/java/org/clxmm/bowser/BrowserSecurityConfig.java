package org.clxmm.bowser;

import org.clxmm.authentication.AbstractChannelSecurityConfig;
import org.clxmm.authentication.ImoocAuthenctiationFailureHandler;
import org.clxmm.authentication.ImoocAuthenticationSuccessHandler;
import org.clxmm.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import org.clxmm.properties.core.BrowserProperties;
import org.clxmm.properties.core.SecurityConstants;
import org.clxmm.properties.core.SecurityProperties;
import org.clxmm.validate.code.SmsCodeFilter;
import org.clxmm.validate.code.ValidateCodeFilter;
import org.clxmm.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * @author clx
 * @date 2020-06-26 15:50
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {


    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    ImoocAuthenctiationFailureHandler imoocAuthenctiationFailureHandler;

    @Autowired
    ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;


    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)

                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        "/code/image"


                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();


    }


    /*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用默认的表单登录
       *//* http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();*//*


        ValidateCodeFilter filter = new ValidateCodeFilter();
        filter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
        filter.setSecurityProperties(securityProperties);
        filter.afterPropertiesSet();


        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();





        BrowserProperties browser = securityProperties.getBrowser();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //添加自己的过滤器
                .formLogin()
//                     .loginPage("/login_c.html")
                    .loginPage("/authentication/require")
                    //自定义登录的post请求  默认login  post
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(imoocAuthenticationSuccessHandler)
                    .failureHandler(imoocAuthenctiationFailureHandler)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(browser.getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                //放行
                .antMatchers("/authentication/require",
                        browser.getLoginPage(),
                        "/code/*","/code/image")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
            //添加smsCodeAuthenticationSecurityConfig的过滤器到浏览器过滤器链的后面
        .apply(smsCodeAuthenticationSecurityConfig);

    }*/


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(".html");
    }
}
