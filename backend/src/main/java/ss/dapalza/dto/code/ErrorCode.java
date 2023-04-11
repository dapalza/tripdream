package ss.dapalza.dto.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    // Member
    // register
    EMAIL_DUPLICATION(400, "M001", "중복된 이메일입니다."),

    // login
    LOGIN_INPUT_INVALID(400, "M002", "회원정보가 없습니다."),
    PASSWORD_INCORRECT(400, "M003", "비밀번호가 틀렸습니다.")

    ;

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
