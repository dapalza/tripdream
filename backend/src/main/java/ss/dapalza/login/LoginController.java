package ss.dapalza.login;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ss.dapalza.common.exception.CustomNotFoundException;
import ss.dapalza.dto.login.LoginEvent;
import ss.dapalza.dto.login.LoginInfo;
import ss.dapalza.dto.login.LoginToken;
import ss.dapalza.entity.Customer;
import ss.dapalza.entity.DPZToken;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping(value="/signIn")
    public ResponseEntity<JSONObject> signInRest(@RequestBody Customer customerT) {
        JSONObject body = new JSONObject();
        Customer customer = loginService.login(customerT);
        LoginEvent le = new LoginEvent(customer);
        LoginInfo li = new LoginInfo(customer);
        LoginToken lt = new LoginToken();
        DPZToken token = loginService.makeToken(customer.getNo(),lt);
        if(token.getIsUse()){
            body.put("customer",li);
            body.put("event",le);
            body.put("token",lt);
            body.put("status", "200");
            return new ResponseEntity<>(body,HttpStatus.OK);
        }
        else {
            throw new CustomNotFoundException();
        }
    }
}
