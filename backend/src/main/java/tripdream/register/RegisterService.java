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
        Member savedMember = repository.save(member);
        log.info("member created at = {}", savedMember.getCreatedAt());
        log.info("member modified at = {}", savedMember.getLastModifiedAt());
        return savedMember;
    }

    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

}