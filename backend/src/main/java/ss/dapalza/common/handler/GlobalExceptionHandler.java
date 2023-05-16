package ss.dapalza.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ss.dapalza.common.exception.ErrorCode;
import ss.dapalza.common.exception.LoginInputInvalidException;
import ss.dapalza.common.exception.PasswordIncorrectException;
import ss.dapalza.common.exception.ValidCheckException;
import ss.dapalza.dto.res.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindingException(BindException be) {
        BindingResult bindingResult = be.getBindingResult();
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BINDING_EXCEPTION, bindingResult.getFieldErrors());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ErrorCode.BINDING_EXCEPTION.getStatus()));
    }

    @ExceptionHandler(ValidCheckException.class)
    public ResponseEntity<ErrorResponse> handleValidCheckException(ValidCheckException exception) {
        ErrorResponse response = new ErrorResponse(ErrorCode.LOGIN_INPUT_INVALID, exception.getFieldErrors());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.LOGIN_INPUT_INVALID.getStatus()));
    }

    @ExceptionHandler(LoginInputInvalidException.class)
    public ResponseEntity<ErrorResponse> handleLoginInputInvalidException(LoginInputInvalidException exception) {
        ErrorResponse response = new ErrorResponse(ErrorCode.LOGIN_INPUT_INVALID, exception.getFieldErrors());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.LOGIN_INPUT_INVALID.getStatus()));
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(PasswordIncorrectException exception) {
        ErrorResponse response = new ErrorResponse(ErrorCode.PASSWORD_INCORRECT, exception.getFieldErrors());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.PASSWORD_INCORRECT.getStatus()));
    }

}
