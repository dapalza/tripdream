package tripdream.common.exception;

public class MalformedTokenException extends JwtTokenException{

    public MalformedTokenException() {
        super(ErrorCode.MALFORMED_TOKEN);
    }

    public MalformedTokenException(Throwable e) {
        super(ErrorCode.MALFORMED_TOKEN, e);
    }
}
