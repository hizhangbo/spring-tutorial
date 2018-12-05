package top.crazybanana.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.crazybanana.security.core.properties.SecurityProperties;

/**
 * @author: Bob
 * @Datetime: 2018-12-03-1:12
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

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
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/require")
//                http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        "/login",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
