package tripdream.common.exception;

import lombok.Getter;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@Getter
public class ValidCheckException extends BindException {
    private ErrorCode errorCode;

    public ValidCheckException(BindingResult bindingResult, ErrorCode errorCode) {
        super(bindingResult);
        this.errorCode = errorCode;
    }
}
