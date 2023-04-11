package ss.dapalza.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ss.dapalza.common.exception.CustomNotFoundException;
import ss.dapalza.dto.code.ErrorCode;
import ss.dapalza.dto.res.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementFoundException(CustomNotFoundException exception) {
        final ErrorResponse response = new ErrorResponse(ErrorCode.LOGIN_INPUT_INVALID);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
