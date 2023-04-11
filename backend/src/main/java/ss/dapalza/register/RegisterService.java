package ss.dapalza.register;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ss.dapalza.dto.req.RegisterRequest;
import ss.dapalza.dto.res.RegisterResponse;
import ss.dapalza.entity.Customer;
import ss.dapalza.entity.Expw;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository repository;
    private final ExPwRepository exRepo;
    private final PasswordEncoder passwordEncoder;

    public Customer registerCustomer(RegisterRequest req) {
        String h_pw = hashPassword(req.getPassword());
        Customer customer = new Customer(req, h_pw);
        Expw expw = new Expw(req.getPassword(), customer);
        Customer result = repository.save(customer);
        exRepo.save(expw);
        return result;
    }

    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    // public boolean checkPassword(String pw, String ex_pw) {
    //     return passwordEncoder.matches(pw, ex_pw);
    // }
}