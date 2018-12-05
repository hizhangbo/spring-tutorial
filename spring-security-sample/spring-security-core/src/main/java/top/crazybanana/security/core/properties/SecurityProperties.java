package top.crazybanana.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Bob
 * @Datetime: 2018-12-05 0:56
 */
@Data
@ConfigurationProperties(prefix = "customer.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
