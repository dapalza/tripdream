package ss.dapalza.common.exception;

import org.springframework.validation.BindingResult;

public class PasswordIncorrectException extends ValidCheckException {
    public PasswordIncorrectException(BindingResult bindingResult, ErrorCode errorCode) {
        super(bindingResult, errorCode);
    }
}
