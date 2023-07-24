package tripdream.common.exception;

public class DifferentIpException extends CustomLoginException{
    public DifferentIpException(Throwable cause) {
        super(ErrorCode.IP_DIFFERENCE, cause);
    }

    public DifferentIpException() {
        super(ErrorCode.IP_DIFFERENCE);
    }
}
