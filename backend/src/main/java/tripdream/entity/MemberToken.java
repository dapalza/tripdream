package tripdream.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tripdream.dto.login.LoginToken;

@Entity
@Getter
@NoArgsConstructor
public class MemberToken extends CommonTimeEntity{
    
    @Id
    @Column(name = "token_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
    
    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_use")
    private int isUse;

    void setMemberToken(LoginToken lt){
        this.accessToken= lt.getAccessToken();
        this.refreshToken= lt.getRefreshToken();
        this.isUse = 1;
    }
    public boolean getIsUse(){
        return this.isUse==1 ? true:false;
    }

}
