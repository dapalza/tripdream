package ss.dapalza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ss.dapalza.dto.req.RegisterRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 이메일
    @NotBlank
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
    private String nickname;

    // 탈퇴 날짜
    private LocalDateTime resigned_date;

    public Member(RegisterRequest req, String h_pw) {
        this.email = req.getEmail();
        this.password = h_pw;
        this.gender = req.getGender();
        this.birth = req.getBirth();
        this.locked = req.getLocked();
        this.nickname = req.getNickname();
    }

}
