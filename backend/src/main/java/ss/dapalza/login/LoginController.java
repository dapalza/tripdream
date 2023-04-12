package ss.dapalza.login;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ss.dapalza.common.exception.LoginInputInvalidException;
import ss.dapalza.dto.login.LoginEvent;
import ss.dapalza.dto.login.LoginInfo;
import ss.dapalza.dto.login.LoginToken;
import ss.dapalza.dto.res.ErrorResponse;
import ss.dapalza.dto.res.LoginResponse;
import ss.dapalza.entity.Customer;
import ss.dapalza.entity.DPZToken;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping(value="/signIn")
    // @CrossOrigin(origins = {"http://localhost:8084"}, allowedHeaders = {"Authorization"})
    @Operation(summary = "로그인", description = "/api/signIn으로 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = LoginResponse.class),
            @ApiResponse(code = 400, message = "못 찾음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    public ResponseEntity<LoginResponse> signInRest(@Validated @RequestBody Customer customerT, BindingResult bindingResult) throws BindException {

        if(bindingResult.hasErrors()) {
            throw new LoginInputInvalidException(bindingResult);
        }

        Customer customer = loginService.login(customerT);
        LoginEvent le = new LoginEvent(customer);
        LoginInfo li = new LoginInfo(customer);
        LoginToken lt = new LoginToken();
        DPZToken token = loginService.makeToken(customer.getNo(),lt);

        if(token.getIsUse()){
            LoginResponse loginResponse = new LoginResponse(le, li, lt, 200);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        else {
            throw new LoginInputInvalidException(bindingResult);
        }
    }
}
