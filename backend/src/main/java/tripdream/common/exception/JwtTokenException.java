package tripdream.common.exception;

public class JwtTokenException extends BusinessException {

    public JwtTokenException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public JwtTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
