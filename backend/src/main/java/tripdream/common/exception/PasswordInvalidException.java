package tripdream.common.exception;

public class PasswordInvalidException extends CustomLoginException {
    public PasswordInvalidException() {
        super(ErrorCode.PASSWORD_INVALID);
    }
}
