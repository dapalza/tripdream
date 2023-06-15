package tripdream.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tripdream.common.vo.login.LoginToken;
import tripdream.common.dto.req.RegisterRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@Builder
public class Member extends CommonTimeEntity implements UserDetails {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 이메일
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String email;

    // 비밀번호
    @NotBlank
    @NotNull
    private String password;

    // 성별 = N - 빈값, M - 남자, F - 여자
    private String gender;

    // 생일 (yyyy-MM-dd)
    private LocalDate birth;

    // 계정 잠금 여부
    @NotBlank
    @NotNull
    private String locked;

    // 닉네임 (중복 없음)
    @NotBlank
    @NotNull
    private String nickname;

    // 탈퇴 날짜
    private LocalDateTime resigned_date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id")
    private MemberToken memberToken;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Member() {

    }

    public void changeMemberToken(MemberToken memberToken) {
        this.memberToken = memberToken;
    }

    // 비밀번호 암호화
    public void hidePassword(String password) {
        this.password = password;
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
