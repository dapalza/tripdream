package tripdream.common.exception;

public class PasswordIncorrectException extends CustomLoginException {
    public PasswordIncorrectException() {
        super(ErrorCode.PASSWORD_INCORRECT);
    }
}
