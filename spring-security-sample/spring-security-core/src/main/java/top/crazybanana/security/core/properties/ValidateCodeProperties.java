package top.crazybanana.security.core.properties;

import lombok.Data;

/**
 * @author: Bob
 * @Datetime: 2018-12-09 21:31
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
}
