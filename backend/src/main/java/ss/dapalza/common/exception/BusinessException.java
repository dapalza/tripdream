package ss.dapalza.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    public BusinessException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
