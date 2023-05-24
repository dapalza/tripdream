package tripdream.common.exception;

import org.springframework.validation.BindingResult;

public class LoginInputInvalidException extends ValidCheckException {
    public LoginInputInvalidException(BindingResult bindingResult, ErrorCode errorCode) {
        super(bindingResult, errorCode);
    }
}
