package ss.dapalza.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ss.dapalza.dto.login.LoginEvent;
import ss.dapalza.dto.login.LoginInfo;
import ss.dapalza.dto.login.LoginToken;
import ss.dapalza.entity.Customer;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private LoginEvent le;

    private LoginInfo li;

    private LoginToken lt;

    private int status;

}
