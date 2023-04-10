package ss.dapalza.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ss.dapalza.util.TokenGenerator;

@Getter
@AllArgsConstructor
public class LoginToken {
    private String accessToken;
    private String refreshToken;
    private int accessTime;
    private int refreshTime;

    public LoginToken(){
        this.accessToken = TokenGenerator.generateAccessToken();
        this.refreshToken = TokenGenerator.generateRefreshToken();
        this.accessTime = 60*10;
        this.refreshTime = 60*60 * 8;
    }
}
