package tripdream.common.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tripdream.common.dto.login.LoginToken;
import tripdream.common.entity.Member;
import tripdream.common.entity.MemberToken;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private LoginToken lt;

    // 생성 날짜
    private LocalDateTime createdDate;

    // 수정 날짜
    private LocalDateTime lastModifiedDate;

    public LoginResponse(Member member) {
        MemberToken memberToken = member.getMemberToken();

        this.lt = new LoginToken();
        this.createdDate = member.getCreatedAt();
        this.lastModifiedDate = member.getLastModifiedAt();
    }

}
