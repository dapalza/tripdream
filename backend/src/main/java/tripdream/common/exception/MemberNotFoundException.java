package tripdream.common.exception;

import org.springframework.validation.BindingResult;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }

    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
