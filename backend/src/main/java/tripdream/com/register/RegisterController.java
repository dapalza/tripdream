package tripdream.com.register;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tripdream.com.common.exception.ErrorCode;
import tripdream.com.common.exception.LoginInputInvalidException;
import tripdream.com.dto.req.RegisterRequest;
import tripdream.com.dto.res.ErrorResponse;
import tripdream.com.dto.res.RegisterResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
@CrossOrigin(origins = {"http://localhost:8082"}, allowedHeaders = {"Authorization"})
public class RegisterController {

    private final RegisterService service;

    @PostMapping()
    @Operation(summary = "회원가입", description = "/api/register로 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = RegisterResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    public ResponseEntity<RegisterResponse> register(@RequestBody @Validated RegisterRequest req, BindingResult bindingResult) throws LoginInputInvalidException {
        if(req.getPassword() == null) {
            throw new LoginInputInvalidException(bindingResult, ErrorCode.LOGIN_INPUT_INVALID);
        }
        RegisterResponse res = new RegisterResponse(service.registerCustomer(req));

       if(res != null) {
           return new ResponseEntity<>(res, HttpStatus.OK);
       } else {
           throw new LoginInputInvalidException(bindingResult, ErrorCode.LOGIN_INPUT_INVALID);
       }

   }
}