package tripdream.login;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import tripdream.common.dao.MemberRepository;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.exception.PasswordIncorrectException;
import tripdream.common.exception.ValidCheckException;
import tripdream.common.util.JwtTokenProvider;
import tripdream.common.dto.login.LoginToken;
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.entity.Member;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService{

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public Member login(LoginRequest loginRequest, BindingResult bindingResult) throws ValidCheckException {

        // 1. login email/pw를 기반으로 Authentication 객체 생성
        // Authentication 에서 현재 인증 여부를 확인하는 authenticated 값 = false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        // 2. 실제 검증(사용자 비밀번호 체크)가 이루어지는 부분
        // authenticate 메서드가 실행될 대 CustomUserDetailService에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        LoginToken loginToken = jwtTokenProvider.generateToken(authentication);

        Member member = memberRepository.findByEmail(loginRequest.getEmail());

        // 이메일에 해당되는 사용자 없음
        if(member == null){
            throw new MemberNotFoundException(bindingResult, ErrorCode.MEMBER_NOT_FOUND);
        }
        // 비밀번호가 틀림
        else if(!checkPassword(loginRequest.getPassword(), member.getPassword())){
            throw new PasswordIncorrectException(bindingResult, ErrorCode.PASSWORD_INCORRECT);
        }

        member.setMemberToken(loginToken);

        return member;
    }

    private final PasswordEncoder passwordEncoder;
    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    public boolean checkPassword(String pw, String ex_pw) {
        return passwordEncoder.matches(pw, ex_pw);
    }

}
