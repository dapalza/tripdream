package tripdream.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class AccessTokenData {
    private String accessToken;

    // 일시 토큰 만료 시간
    private LocalDateTime accessTokenExpireAt;
}
