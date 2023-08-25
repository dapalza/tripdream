package tripdream.member.register;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tripdream.common.dto.req.DuplicateCheckRequest;
import tripdream.common.dto.res.ErrorResponse;
import tripdream.common.dto.res.MemberResponse;
import tripdream.common.entity.Member;
import tripdream.common.exception.LoginInputInvalidException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
@CrossOrigin(origins = {"http://localhost:8084", "http://fleescape.shop:8084"}, allowedHeaders = {"Authorization"})
@Slf4j
public class RegisterController {

    private final RegisterService service;

    private void checkBindingError(BindingResult bindingResult) throws LoginInputInvalidException {
        if(bindingResult.hasErrors())
            throw new LoginInputInvalidException(bindingResult);
    }

    @PostMapping
    @Operation(summary = "회원가입", description = "/api/register로 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = MemberResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    public ResponseEntity<MemberResponse> register(@RequestBody @Validated Member member, BindingResult bindingResult) throws LoginInputInvalidException, IOException {

        checkBindingError(bindingResult);

        MemberResponse res = new MemberResponse(service.registerCustomer(member));
        return new ResponseEntity<>(res, HttpStatus.OK);

   }

    @PostMapping("/check-email")
    public ResponseEntity<Map<String, String>> checkEmailDuplicate(@RequestBody DuplicateCheckRequest request, BindingResult bindingResult) throws LoginInputInvalidException {

        checkBindingError(bindingResult);

        log.info("check email duplicated = {}", request.getEmail());

        if(request.getEmail() == null) {
            throw new LoginInputInvalidException(bindingResult);
        }

        service.isEmailCanBeUsed(request.getEmail());

        Map<String, String> res = new HashMap<>();
        res.put("message", "사용 가능한 이메일입니다.");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<Map<String, String>> checkNicknameDuplicate(@RequestBody DuplicateCheckRequest request, BindingResult bindingResult) throws LoginInputInvalidException {

        checkBindingError(bindingResult);

        if(request.getNickname() == null) {
            throw new LoginInputInvalidException(bindingResult);
        }

        service.isNicknameCanBeUsed(request.getNickname());

        Map<String, String> res = new HashMap<>();
        res.put("message", "사용 가능한 닉네임입니다.");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}