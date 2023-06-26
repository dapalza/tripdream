package tripdream.common.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
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
    private LocalDate birth;

    // 계정 잠금 여부
    @NotBlank
    private String locked;

    // 닉네임 (중복 없음)
    private String nickname;

}
