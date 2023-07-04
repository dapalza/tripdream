package tripdream.common.exception;

import org.springframework.validation.BindingResult;

public class PasswordIncorrectException extends ValidCheckException {
    public PasswordIncorrectException(BindingResult bindingResult) {
        super(bindingResult, ErrorCode.PASSWORD_INCORRECT);
    }
}
