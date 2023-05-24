package tripdream.com.login;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tripdream.com.common.exception.ErrorCode;
import tripdream.com.common.exception.LoginInputInvalidException;
import tripdream.com.common.exception.ValidCheckException;
import tripdream.com.dto.req.LoginRequest;
import tripdream.com.dto.res.ErrorResponse;
import tripdream.com.dto.res.LoginResponse;
import tripdream.com.entity.MemberToken;
import tripdream.com.entity.Member;

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
    public ResponseEntity<LoginResponse> signInRest(@Validated @RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws BindException {

        // 유효성 검사 체크
        if(bindingResult.hasErrors()) {
            log.info("what binding error = {}", bindingResult.getFieldErrors());
            throw new ValidCheckException(bindingResult, ErrorCode.VALID_EXCEPTION);
        }

        Member member = loginService.login(loginRequest, bindingResult);
        MemberToken token = member.getMemberToken();

        if(token.getIsUse()){
            LoginResponse loginResponse = new LoginResponse(member);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        else {
            throw new LoginInputInvalidException(bindingResult, ErrorCode.LOGIN_INPUT_INVALID);
        }
    }
}
