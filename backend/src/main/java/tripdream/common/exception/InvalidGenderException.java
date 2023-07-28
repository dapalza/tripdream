package tripdream.common.exception;

public class InvalidGenderException extends BusinessException{
    public InvalidGenderException() {
        super(ErrorCode.INVALID_GENDER);
    }
}
