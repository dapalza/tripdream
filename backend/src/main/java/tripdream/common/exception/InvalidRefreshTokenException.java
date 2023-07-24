package tripdream.common.exception;

public class InvalidRefreshTokenException extends CustomTokenException{
    public InvalidRefreshTokenException(Throwable cause) {
        super(ErrorCode.INVALID_REFRESH_TOKEN, cause);
    }

    public InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
