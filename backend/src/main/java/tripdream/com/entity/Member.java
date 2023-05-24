package tripdream.com.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import tripdream.com.dto.login.LoginToken;
import tripdream.com.dto.req.RegisterRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Member extends CommonTimeEntity{

    @Id
    @Column(name = "member_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 이메일
    @NotBlank
    @Column(unique = true)
    private String email;

    // 비밀번호
    @NotBlank
    private String password;

    // 성별 = N - 빈값, M - 남자, F - 여자
    private String gender;

    // 생일 (yyyy-MM-dd)
    private Date birth;

    // 계정 잠금 여부
    @NotBlank
    private String locked;

    // 닉네임 (중복 없음)
    @Column(unique = true)
    private String nickname;

    // 탈퇴 날짜
    private LocalDateTime resigned_date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id")
    private MemberToken memberToken;

    public Member(RegisterRequest req, String h_pw) {
        this.email = req.getEmail();
        this.password = h_pw;
        this.gender = req.getGender();
        this.birth = req.getBirth();
        this.locked = req.getLocked();
        this.nickname = req.getNickname();
    }

    public void setMemberToken(LoginToken loginToken) {
        this.memberToken.setMemberToken(loginToken);
    }
}
