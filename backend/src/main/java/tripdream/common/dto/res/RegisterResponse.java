package tripdream.common.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import tripdream.common.entity.Member;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class RegisterResponse {
    // 이메일
    @NotBlank
    private String email;

    // 비밀번호
    @NotBlank
    private String password;

    // 성별 = N - 빈값, M - 남자, F - 여자
    private String gender;

    // 생일 (yyyy-MM-dd)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    // 계정 잠금 여부
    @NotBlank
    private boolean locked;

    // 닉네임 (중복 없음)
    private String nickname;

    // 탈퇴 날짜
    private LocalDateTime resigned_date;

    // 생성 날짜
    private LocalDateTime createdAt;

    // 수정 날짜
    private LocalDateTime lastModifiedAt;

    public RegisterResponse(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.gender = member.getGender();
        this.createdAt = member.getCreatedAt();
        this.lastModifiedAt = member.getLastModifiedAt();
    }
}
