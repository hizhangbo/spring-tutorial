package top.crazybanana.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.crazybanana.security.core.properties.SecurityProperties;

/**
 * @author: Bob
 * @Datetime: 2018-12-05 0:59
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
