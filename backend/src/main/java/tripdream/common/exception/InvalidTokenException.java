package tripdream.common.exception;

public class InvalidTokenException extends JwtTokenException{

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

    public InvalidTokenException(Throwable e) {
        super(ErrorCode.INVALID_TOKEN, e);
    }
}
