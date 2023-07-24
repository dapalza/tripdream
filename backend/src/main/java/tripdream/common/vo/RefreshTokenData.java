package tripdream.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class RefreshTokenData {

    private String refreshToken;

    // 장기 토큰 만료 시간
    private LocalDateTime refreshTokenExpireAt;

}
