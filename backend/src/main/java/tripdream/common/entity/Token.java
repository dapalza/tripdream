package tripdream.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token extends CommonEntity{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "TOKEN_ID")
    private String id;

    private String accessToken;

    // 일시 토큰 만료 시간
    private LocalDateTime accessTokenExpireAt;

    private String refreshToken;

    // 장기 토큰 만료 시간
    private LocalDateTime refreshTokenExpireAt;

    // 사용 여부 (만료 여부)
    private char useYn;

    public void refreshAccessToken(String accessToken, int accessTokenExpireLong) {
        this.accessToken = accessToken;
        this.accessTokenExpireAt = LocalDateTime.now().plusMinutes(accessTokenExpireLong);
    }

}
