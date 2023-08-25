package tripdream.common.exception;

public class NoSuchPlanException extends BusinessException{
    public NoSuchPlanException(Throwable cause) {
        super(ErrorCode.NO_SUCH_PLAN, cause);
    }

    public NoSuchPlanException() {
        super(ErrorCode.NO_SUCH_PLAN);
    }
}
