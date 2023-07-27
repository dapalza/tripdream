package tripdream.register;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
import tripdream.common.dto.res.ErrorResponse;
import tripdream.common.dto.res.RegisterResponse;
import tripdream.common.entity.Member;
import tripdream.common.exception.LoginInputInvalidException;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
@CrossOrigin(origins = {"http://localhost:8084", "http://fleescape.shop:8084"}, allowedHeaders = {"Authorization"})
public class RegisterController {

    private final RegisterService service;

    private static void checkBindingError(BindingResult bindingResult) throws LoginInputInvalidException {
        if(bindingResult.hasErrors())
            throw new LoginInputInvalidException(bindingResult);
    }

    @PostMapping
    @Operation(summary = "회원가입", description = "/api/register로 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = RegisterResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    public ResponseEntity<RegisterResponse> register(@RequestBody @Validated Member member, BindingResult bindingResult) throws LoginInputInvalidException {

        checkBindingError(bindingResult);

        RegisterResponse res = new RegisterResponse(service.registerCustomer(member));
        return new ResponseEntity<>(res, HttpStatus.OK);

   }

    @PostMapping("/check-email")
    public ResponseEntity<Map<String, String>> checkEmailDuplicate(@Validated String email, BindingResult bindingResult) throws LoginInputInvalidException {

        checkBindingError(bindingResult);

        service.isEmailCanBeUsed(email);

        Map<String, String> res = new HashMap<>();
        res.put("message", "사용 가능한 이메일입니다.");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<Map<String, String>> checkNicknameDuplicate(@Validated String nickname, BindingResult bindingResult) throws LoginInputInvalidException {

        checkBindingError(bindingResult);

        service.isNicknameCanBeUsed(nickname);

        Map<String, String> res = new HashMap<>();
        res.put("message", "사용 가능한 닉네임입니다.");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}