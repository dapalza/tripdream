package ss.dapalza.login;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ss.dapalza.dto.res.ErrorResponse;
import ss.dapalza.dto.res.LoginResponse;
import ss.dapalza.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService loginService;


    @PostMapping(value="/signIn")
    public ResponseEntity<JSONObject> signInRest(@RequestBody Customer customerT) {
        JSONObject body = new JSONObject();
        LoginResponse res = new LoginResponse(loginService.login(customerT));
        if(res != null){
            body.put("res",res);
            body.put("status", "200");
            return new ResponseEntity<>(body,HttpStatus.OK);
        }
        else {
            body.put("error",ErrorResponse.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }
}
