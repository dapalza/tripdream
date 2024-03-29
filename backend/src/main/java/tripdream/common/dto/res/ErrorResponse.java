package tripdream.common.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;
import tripdream.common.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    // 문자열 에러 메시지
    private String message;
    
    // HTTP 상태코드
    private int status;
    
    // 자체 에러코드
    private String code;

    private List<FieldError> fieldErrors = new ArrayList<>();

    public ErrorResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
    }

    public ErrorResponse(ErrorCode errorCode, List<FieldError> fieldErrors) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.fieldErrors = fieldErrors;
    }

    private static class CustomFieldError {
        // 문자열 에러 메시지
        private String message;

        // HTTP 상태코드
        private int status;

        // 자체 에러코드
        private String code;
    }

    public void changeMessage(String msg) {
        message = msg;
    }

}
