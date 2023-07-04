package tripdream.common.exception;

public class FileNotFoundException extends BusinessException{

    public FileNotFoundException() {
        super(ErrorCode.FILE_NOT_FOUND);
    }

    public FileNotFoundException(Throwable cause) {
        super(ErrorCode.FILE_NOT_FOUND, cause);
    }
}
