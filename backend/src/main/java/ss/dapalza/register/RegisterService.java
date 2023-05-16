package ss.dapalza.register;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ss.dapalza.dto.req.RegisterRequest;
import ss.dapalza.entity.Member;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Member registerCustomer(RegisterRequest req) {
        String h_pw = hashPassword(req.getPassword());
        Member member = new Member(req, h_pw);
        Member result = repository.save(member);
        return result;
    }

    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    // public boolean checkPassword(String pw, String ex_pw) {
    //     return passwordEncoder.matches(pw, ex_pw);
    // }
}