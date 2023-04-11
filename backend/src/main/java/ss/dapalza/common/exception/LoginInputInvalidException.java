package ss.dapalza.common.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LoginInputInvalidException extends BindException {
    public LoginInputInvalidException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
