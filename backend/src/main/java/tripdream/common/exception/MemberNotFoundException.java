package tripdream.common.exception;

import org.springframework.validation.BindingResult;

public class MemberNotFoundException extends ValidCheckException {
    public MemberNotFoundException(BindingResult bindingResult, ErrorCode errorCode) {
        super(bindingResult, errorCode);
    }
}
