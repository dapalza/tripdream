package tripdream.common.exception;

public class RefreshTokenExpiredException extends CustomTokenException{
    public RefreshTokenExpiredException(Throwable cause) {
        super(ErrorCode.EXPIRED_REFRESH_TOKEN, cause);
    }

    public RefreshTokenExpiredException() {
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
