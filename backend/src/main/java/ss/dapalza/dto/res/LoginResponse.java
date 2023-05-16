package ss.dapalza.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ss.dapalza.dto.login.LoginToken;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private LoginToken lt;

    private int status;

}
