package tripdream.register;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tripdream.common.dao.MemberRepository;
import tripdream.common.entity.Member;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Member registerCustomer(Member member) {
        log.info("hashing password start");

        String hashPassword = hashPassword(member.getPassword());

        member.hidePassword(hashPassword);

        log.info("hashing password end");

        return repository.save(member);
    }

    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

}