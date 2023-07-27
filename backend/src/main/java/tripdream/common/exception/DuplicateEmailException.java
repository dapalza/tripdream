package tripdream.common.exception;

public class DuplicateEmailException extends DuplicateException{
    public DuplicateEmailException(Throwable cause) {
        super(ErrorCode.EMAIL_DUPLICATION, cause);
    }

    public DuplicateEmailException() {
        super(ErrorCode.EMAIL_DUPLICATION);
    }
}
