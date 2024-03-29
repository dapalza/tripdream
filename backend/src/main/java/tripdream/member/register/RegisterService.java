package tripdream.member.register;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripdream.common.entity.Member;
import tripdream.common.exception.*;
import tripdream.common.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member registerCustomer(Member member) {

        if(memberRepository.findByEmailOrNickname(member.getEmail(), member.getNickname()).isPresent()) {
            throw new DuplicateException(ErrorCode.EMAIL_DUPLICATION);
        }

        if (!member.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$")){
            throw new PasswordInvalidException();
        }

        if(member.getGender() != null) {
            if (! (member.getGender().equals("M") || member.getGender().equals("F"))) {
                throw new InvalidGenderException();
            }
        }


        log.info("hashing password start");

        String hashPassword = hashPassword(member.getPassword());

        member.hidePassword(hashPassword);
        member.storeRoles("MEMBER");

        log.info("hashing password end");

        return memberRepository.save(member);
    }

    public void isEmailCanBeUsed(String email) {
        // 이메일 중복 시 예외처리
        if(memberRepository.findByEmail(email).isPresent())
            throw new DuplicateEmailException();
    }

    public void isNicknameCanBeUsed(String nickname) {
        // 닉네임 중복 시 예외처리
        if(memberRepository.findByNickname(nickname).isPresent())
            throw new DuplicateNicknameException();
    }


    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

}