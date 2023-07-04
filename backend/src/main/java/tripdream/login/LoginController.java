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
import org.springframework.web.bind.annotation.*;
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.dto.res.ErrorResponse;
import tripdream.common.dto.res.LoginResponse;
import tripdream.common.entity.Member;
import tripdream.common.entity.Token;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.exception.ValidCheckException;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping(value= "/login")
    @CrossOrigin(origins = {
            "http://localhost:8084",
            "http://fleescape.shop:8084"
    }, allowedHeaders = {"Authorization"})
    @Operation(summary = "로그인", description = "/api/login으로 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = LoginResponse.class),
            @ApiResponse(code = 400, message = "못 찾음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    public ResponseEntity<LoginResponse> signInRest(@Validated @RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws BindException {

        log.info("time is gold = {}", LocalDateTime.now());

        // 유효성 검사 체크
        if(bindingResult.hasErrors()) {
            log.info("what binding error = {}", bindingResult.getFieldErrors());
            throw new ValidCheckException(bindingResult, ErrorCode.VALID_EXCEPTION);
        }

        log.info("before login");

        Member member = loginService.login(loginRequest);

        log.info("after login");
        Token token = member.getToken();

        log.info("token lets go = {}", token.getAccessToken());

        if(token != null){
            LoginResponse loginResponse = new LoginResponse(member);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        else {
            throw new MemberNotFoundException();
        }
    }
}
