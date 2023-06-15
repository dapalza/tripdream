package tripdream.common.exception;

public class DuplicateNicknameException extends DuplicateException{
    public DuplicateNicknameException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DuplicateNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
