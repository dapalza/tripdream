package tripdream.common.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import tripdream.common.entity.Gender;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDataChangeRequest {
    @NotBlank
    private String email;

    private String password;

    // 성별 = N - 빈값, M - 남자, F - 여자
    @Nullable
    private Gender gender;

    // 생일 (yyyy-MM-dd)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    // 계정 잠금 여부
    private Boolean locked;

    // 닉네임 (중복 없음)
    private String nickname;

    // 탈퇴 날짜
    private LocalDate resigned_date;
}
