package tripdream.login;

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
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.dto.res.ErrorResponse;
import tripdream.common.dto.res.LoginResponse;
import tripdream.common.entity.Member;
import tripdream.common.entity.MemberToken;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.exception.ValidCheckException;

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

        log.info("before login");

        Member member = loginService.login(loginRequest);

        log.info("after login");
        MemberToken token = member.getMemberToken();

        log.info("token lets go = {}", token.getAccessToken());

        if(token != null){
            LoginResponse loginResponse = new LoginResponse(member);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        else {
            throw new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND);
        }
    }
}
