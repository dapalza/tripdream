package ss.dapalza.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "error.invalid.password")
public class PasswordIncorrectException extends BindException {
    public PasswordIncorrectException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
