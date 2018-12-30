package top.crazybanana.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: Bob
 * @Datetime: 2018-12-09 19:41
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -8934344362037459795L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
