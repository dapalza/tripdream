package ss.dapalza.login;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ss.dapalza.dto.res.ErrorResponse;
import ss.dapalza.dto.res.LoginResponse;
import ss.dapalza.entity.Customer;
import ss.dapalza.entity.LoginEvent;
import ss.dapalza.entity.LoginInfo;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService loginService;


    @PostMapping(value="/signIn")
    public ResponseEntity<JSONObject> signInRest(@RequestBody Customer customerT) {
        JSONObject body = new JSONObject();
        Customer customer = loginService.login(customerT);
        LoginEvent le = new LoginEvent(customer);
        LoginInfo li = new LoginInfo(customer);
        if(li != null){
            body.put("customer",li);
            body.put("event",le);
            body.put("status", "200");
            return new ResponseEntity<>(body,HttpStatus.OK);
        }
        else {
            body.put("error",ErrorResponse.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }
}
