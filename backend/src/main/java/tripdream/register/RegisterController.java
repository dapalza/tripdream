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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8084"}, allowedHeaders = {"Authorization"})
public class RegisterController {

    private final RegisterService service;

    @PostMapping(value="/register")
    @Operation(summary = "회원가입", description = "/api/register로 요청")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = RegisterResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    public ResponseEntity<RegisterResponse> register(@RequestBody @Validated Member member, BindingResult bindingResult) throws LoginInputInvalidException {

        RegisterResponse res = new RegisterResponse(service.registerCustomer(member));

       if(res != null) {
           return new ResponseEntity<>(res, HttpStatus.OK);
       } else {
           throw new LoginInputInvalidException(bindingResult);
       }

   }
}