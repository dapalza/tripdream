package ss.dapalza.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ss.dapalza.dto.res.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException be) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BINDING_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ErrorCode.BINDING_EXCEPTION.getStatus()));
    }

    @ExceptionHandler(ValidCheckException.class)
    public ResponseEntity<ErrorResponse> handleValidCheckException(ValidCheckException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse response = new ErrorResponse(errorCode, exception.getFieldErrors());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

}
