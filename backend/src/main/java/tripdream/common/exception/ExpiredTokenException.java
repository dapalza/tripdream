package tripdream.common.exception;

public class ExpiredTokenException extends JwtTokenException {

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

    public ExpiredTokenException(Throwable cause) {
        super(ErrorCode.EXPIRED_TOKEN, cause);
    }
}
