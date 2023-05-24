package tripdream.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tripdream.dto.login.LoginToken;
import tripdream.dto.req.RegisterRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
public class Member extends CommonTimeEntity implements UserDetails {

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Member(RegisterRequest req, String h_pw) {
        this.email = req.getEmail();
        this.password = h_pw;
        this.gender = req.getGender();
        this.birth = req.getBirth();
        this.locked = req.getLocked();
        this.nickname = req.getNickname();
    }

    public Member() {

    }

    public void setMemberToken(LoginToken loginToken) {
        this.memberToken.setMemberToken(loginToken);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
