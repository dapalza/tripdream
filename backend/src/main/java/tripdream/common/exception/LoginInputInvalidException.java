package tripdream.common.exception;

import org.springframework.validation.BindingResult;

public class LoginInputInvalidException extends ValidCheckException {
    public LoginInputInvalidException(BindingResult bindingResult) {
        super(bindingResult, ErrorCode.INVALID_INPUT_VALUE);
    }
}
