package tripdream.common.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tripdream.common.vo.LoginTokenVO;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Token extends CommonEntity{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "TOKEN_ID")
    private String id;

    private String accessToken;

    private String refreshToken;

    // 사용 여부 (만료 여부)
    private char useYn;

    // 일시 토큰 만료 시간
    private LocalDateTime accessTokenExpireAt;

    // 장기 토큰 만료 시간
    private LocalDateTime refreshTokenExpireAt;

    public Token(LoginTokenVO loginTokenVO) {
        this.accessToken= loginTokenVO.getAccessToken();
        this.refreshToken= loginTokenVO.getRefreshToken();
        this.accessTokenExpireAt = loginTokenVO.getAccessTokenExpireAt();
        this.refreshTokenExpireAt = loginTokenVO.getRefreshTokenExpireAt();
        this.useYn = 'Y';
    }

}
