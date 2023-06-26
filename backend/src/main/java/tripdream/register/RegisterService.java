package tripdream.register;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripdream.common.repository.MemberRepository;
import tripdream.common.entity.Member;
import tripdream.common.exception.DuplicateNicknameException;
import tripdream.common.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member registerCustomer(Member member) {

        // 닉네임 중복 시 예외처리
        log.info("is nickname not found = {}", repository.findByNickname(member.getNickname()).isEmpty());
        if(!repository.findByNickname(member.getNickname()).isEmpty())
            throw new DuplicateNicknameException(ErrorCode.NICKNAME_DUPLICATION);

        log.info("hashing password start");

        String hashPassword = hashPassword(member.getPassword());

        member.hidePassword(hashPassword);
        member.storeRoles("USER");

        log.info("hashing password end");

        return repository.save(member);
    }

    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

}