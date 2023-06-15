package tripdream.common.exception;

public class DuplicateException extends BusinessException{
    public DuplicateException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DuplicateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
