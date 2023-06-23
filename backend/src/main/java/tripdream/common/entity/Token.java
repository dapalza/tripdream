package tripdream.common.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tripdream.common.vo.LoginToken;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Token extends CommonTimeEntity{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String accessToken;

    private String refreshToken;

    // 사용 여부 (만료 여부)
    private char useYn;

    // 일시 토큰 만료 시간
    private LocalDateTime accessTokenExpireAt;

    // 장기 토큰 만료 시간
    private LocalDateTime refreshTokenExpireAt;

    public Token(LoginToken loginToken) {
        this.accessToken= loginToken.getAccessToken();
        this.refreshToken= loginToken.getRefreshToken();
        this.accessTokenExpireAt = loginToken.getAccessTokenExpireAt();
        this.refreshTokenExpireAt = loginToken.getRefreshTokenExpireAt();
        this.useYn = 'Y';
    }

}
