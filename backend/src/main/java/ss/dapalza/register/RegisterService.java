package ss.dapalza.register;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import ss.dapalza.dto.req.RegisterRequest;
import ss.dapalza.dto.res.RegisterResponse;
import ss.dapalza.entity.Customer;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository repository;

//    public RegisterResponse registerCustomer(RegisterR req) {
//        Customer customer = new Customer(req);
//        Customer result = repository.save(customer);
//        RegisterResponse res = new RegisterResponse(result);
//        return res;
//    }
}