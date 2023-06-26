package tripdream.common.exception;

public class FileNotFoundException extends BusinessException{

    public FileNotFoundException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public FileNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
