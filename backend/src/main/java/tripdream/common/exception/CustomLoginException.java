package tripdream.common.exception;

public class CustomLoginException extends BusinessException{
    public CustomLoginException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CustomLoginException(ErrorCode errorCode) {
        super(errorCode);
    }
}
