package tripdream.common.exception;

public class MemberNotFoundException extends CustomLoginException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(Throwable cause) {
        super(ErrorCode.MEMBER_NOT_FOUND, cause);
    }
}
