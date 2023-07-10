package tripdream.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends CommonEntity implements UserDetails {

    @Id
    @Column(name = "MEMBER_ID")
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
    private Boolean locked;

    // 닉네임 (중복 없음)
    @NotBlank
    @NotNull
    private String nickname;

    // 탈퇴 날짜
    private LocalDate resigned_date;

    // Token 단방향 1:1
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOKEN_ID")
    private Token token;

    // Image 단방향 1:1
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @PrePersist
    private void makeDefault() {
        locked = false;
        resigned_date = LocalDate.of(9999, 12, 31);
    }

    public void changeMemberToken(Token token) {
        this.token = token;
    }

    // 비밀번호 암호화
    public void hidePassword(String password) {
        this.password = password;
    }

    public void storeRoles(String roles) {
        List<String> roleList = List.of(roles.split(","));
        this.roles = roleList;
    }

    // 계정 탈퇴시키기
    public void resignAccount() {
        resigned_date = LocalDate.now();
        locked = true;
    }

    // 하단은 시큐리티 위한 override 메소드들
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
        if(LocalDate.now().isAfter(resigned_date)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(locked) return false;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 60일 이상 지나면 비밀번호 변경
        if(LocalDateTime.now().isAfter(this.getLastModifiedAt().plusDays(60))) return false;
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(locked) return false;
        return true;
    }
}
