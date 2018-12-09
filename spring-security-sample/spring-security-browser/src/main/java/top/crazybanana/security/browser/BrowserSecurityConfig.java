package top.crazybanana.security.browser;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.crazybanana.security.core.properties.SecurityProperties;
import top.crazybanana.security.core.validate.code.ValidateCodeFilter;

import javax.annotation.Resource;

/**
 * @author: Bob
 * @Datetime: 2018-12-03-1:12
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Resource
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 配置表單登錄/Http Basic登錄
     * <p>
     * 過濾器鏈
     * Username Password Authentication Filter (表單登錄)
     * Basic Authentication Filter （Http Basic登錄）
     * <p>
     * Exception Translation Filter（捕獲FilterSecurity Interceptor抛出的異常）
     * FilterSecurity Interceptor (最後的驗證攔截器)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/require")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
//                http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        "/login",
                        "/bootstrap/**",
                        "/css/**",
                        "/images/**",
                        "/music/**",
                        "/scripts/**",
                        "/favicon.ico",
                        "/code/image",
                        "/error/**",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
