package ss.dapalza.login;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ss.dapalza.dto.login.LoginEvent;
import ss.dapalza.dto.login.LoginInfo;
import ss.dapalza.dto.login.LoginToken;
import ss.dapalza.dto.res.ErrorResponse;
import ss.dapalza.entity.Customer;
import ss.dapalza.entity.DPZToken;
import lombok.RequiredArgsConstructor;

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
            body.put("error",ErrorResponse.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }
}
