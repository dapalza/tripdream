package tripdream.common.exception;

public class CustomTokenException extends BusinessException{
    public CustomTokenException(Throwable cause) {
        super(ErrorCode.CUSTOM_TOKEN_EXCEPTION, cause);
    }

    public CustomTokenException() {
        super(ErrorCode.CUSTOM_TOKEN_EXCEPTION);
    }

    public CustomTokenException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CustomTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
