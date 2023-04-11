package ss.dapalza.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ss.dapalza.dto.login.LoginToken;

@Entity
@Getter
@NoArgsConstructor
public class DPZToken {
    
    @Id
    @Column(name = "token_no")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String no;
    
    @Column(name = "customer_no")
    private String customerNo;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "access_time")
    private LocalDateTime accessTime;

    @Column(name = "refresh_time")
    private LocalDateTime refreshTime;

    @Column(name = "is_use")
    private int isUse;

    public DPZToken(String customerNo, LoginToken lt){
        this.customerNo= customerNo;
        this.accessToken= lt.getAccessToken();
        this.refreshToken= lt.getRefreshToken();
        this.accessTime= LocalDateTime.now().plusSeconds(lt.getAccessTime());
        this.refreshTime= LocalDateTime.now().plusSeconds(lt.getRefreshTime());
        this.isUse = 1;
    }
    public boolean getIsUse(){
        return this.isUse==1 ? true:false;
    }

    @PrePersist
    private void setTokenData(){
    }
}
