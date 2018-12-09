package top.crazybanana.security.core.properties;

import lombok.Data;

/**
 * @author: Bob
 * @Datetime: 2018-12-09 21:31
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;
}
