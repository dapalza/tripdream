package ss.dapalza.common.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class ValidCheckException extends BindException {
    public ValidCheckException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
