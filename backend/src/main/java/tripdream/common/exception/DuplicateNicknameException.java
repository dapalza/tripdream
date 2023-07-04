package tripdream.common.exception;

public class DuplicateNicknameException extends DuplicateException{
    public DuplicateNicknameException() {
        super(ErrorCode.NICKNAME_DUPLICATION);
    }

    public DuplicateNicknameException(Throwable cause) {
        super(ErrorCode.NICKNAME_DUPLICATION, cause);
    }
}
