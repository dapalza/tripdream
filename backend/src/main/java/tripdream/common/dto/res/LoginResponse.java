package tripdream.common.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tripdream.common.entity.Member;
import tripdream.common.entity.Token;
import tripdream.common.vo.AccessTokenData;
import tripdream.common.vo.RefreshTokenData;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private AccessTokenData accessTokenData;

    private RefreshTokenData refreshTokenData;

    private String email;

    private Boolean locked;

    public LoginResponse(Member member) {
        Token token = member.getToken();
        this.accessTokenData = new AccessTokenData(token.getAccessToken(), token.getAccessTokenExpireAt());
        this.refreshTokenData = new RefreshTokenData(token.getRefreshToken(), token.getRefreshTokenExpireAt());
        this.email = member.getEmail();
        this.locked = member.getLocked();
    }

}
