package ss.dapalza.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum ErrorResponse {
    NOT_FOUND(404, "not found"),
    SERVER_ERROR(500, "server error");

    private final int code;
    private final String message;

    ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
