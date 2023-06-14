package tripdream.common.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tripdream.common.entity.Member;
import tripdream.common.entity.MemberToken;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private MemberToken memberToken;

    private String email;

    private String locked;

    public LoginResponse(Member member) {
        this.memberToken = member.getMemberToken();
        this.email = member.getEmail();
        this.locked = member.getLocked();
    }

}
