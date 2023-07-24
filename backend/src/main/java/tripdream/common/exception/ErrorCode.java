package tripdream.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    BASIC_ERROR_CODE(500, "C000", "예외가 발생했습니다."),

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

    NICKNAME_DUPLICATION(400, "M006", "중복된 이메일입니다."),

    IP_DIFFERENCE(400, "M007", "작성자와 로그인 IP가 다릅니다."),

    // TOKEN
    CUSTOM_TOKEN_EXCEPTION(400, "T000", "TOKEN 관련 예외입니다."),
    INVALID_TOKEN(400, "T001", "변조된 토큰입니다."),

    MALFORMED_TOKEN(400, "T002", "올바르지 않은 토큰입니다"),

    EXPIRED_TOKEN(400, "T003", "만료된 토큰입니다."),

    SECURITY_EXCEPTION(400, "T004", "인증에 문제가 있습니다."),

    NOT_JWT(400, "T005", "JWT 토큰이 아닙니다."),

    EXPIRED_REFRESH_TOKEN(400, "T006", "Refresh Token이 만료되었습니다."),


    INVALID_REFRESH_TOKEN(400, "T007", "REFRESH TOKEN이 존재하지 않습니다."),

    // File
    FILE_NOT_FOUND(400, "F001", "파일 전송에 실패했습니다.");

    final String code;
    final String message;
    final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
