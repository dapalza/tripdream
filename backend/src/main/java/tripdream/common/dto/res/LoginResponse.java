package tripdream.common.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tripdream.common.entity.Member;
import tripdream.common.entity.Token;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Token token;

    private String email;

    private Boolean locked;

    public LoginResponse(Member member) {
        this.token = member.getToken();
        this.email = member.getEmail();
        this.locked = member.getLocked();
    }

}
