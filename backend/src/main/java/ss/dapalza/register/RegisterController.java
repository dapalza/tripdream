package ss.dapalza.register;

import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ss.dapalza.dto.req.RegisterRequest;
import ss.dapalza.dto.res.ErrorResponse;
import ss.dapalza.dto.res.RegisterResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
@CrossOrigin(origins = {"http://localhost:8082"}, allowedHeaders = {"Authorization"})
public class RegisterController {

    private final RegisterService service;

    @PostMapping()
    @Operation(summary = "회원가입", description = "/api/register로 요청")
    @ApiResponse(code = 200, message = "test message")
    public ResponseEntity<JSONObject> register(@RequestBody RegisterRequest req) {
        JSONObject body = new JSONObject();
        RegisterResponse res = new RegisterResponse(service.registerCustomer(req));

       if(res != null) {
           body.put("res", res);
           return new ResponseEntity<>(body, HttpStatus.OK);
       } else {
           JSONObject error = new JSONObject();
           error.put("error", ErrorResponse.NOT_FOUND);
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
       }

   }
}