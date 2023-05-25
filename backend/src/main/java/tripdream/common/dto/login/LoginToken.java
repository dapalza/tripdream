package tripdream.common.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginToken {

    // JWT 인증 타입 = Bearer
    private String grantType;

    // 일시 토큰
    private String accessToken;
    // 장기 토큰
    private String refreshToken;

}
