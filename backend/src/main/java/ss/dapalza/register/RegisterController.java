package ss.dapalza.register;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import ss.dapalza.dto.req.RegisterRequest;
import ss.dapalza.dto.res.ErrorResponse;
import ss.dapalza.dto.res.RegisterResponse;
import ss.dapalza.entity.Customer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
@CrossOrigin(origins = {"http://localhost:8080"}, allowedHeaders = {"Authorization"})
public class RegisterController {

    private final RegisterService service;

    @PostMapping()
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
