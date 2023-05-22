package ss.dapalza.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "입력값이 틀렸습니다."),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    HANDLE_ACCESS_DENIED(403, "C006", "접근이 거부되었습니다."),
    
    BINDING_EXCEPTION(405, "C003", "바인딩 예외 발생"),
    VALID_EXCEPTION(405, "C004", "유효성 검사 통과 실패"),

    // Member
    // register
    EMAIL_DUPLICATION(400, "M001", "중복된 이메일입니다."),

    // login
    LOGIN_INPUT_INVALID(400, "M002", "회원정보가 없습니다."),

    EMAIL_NOT_FOUND(400, "M003", "없는 이메일입니다."),
    PASSWORD_INCORRECT(400, "M004", "비밀번호가 틀렸습니다."),

    MEMBER_NOT_FOUND(400, "M005", "회원정보가 없습니다."),


    ;

    final String code;
    final String message;
    final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
