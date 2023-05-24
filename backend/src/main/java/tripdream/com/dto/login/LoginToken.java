package tripdream.com.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginToken {

    // JWT 인증 타입 = Bearer
    private String grantType;

    // 일시 토큰
    private String accessToken;
    // 장기 토큰
    private String refreshToken;

}
