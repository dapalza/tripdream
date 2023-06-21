package tripdream.login;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripdream.common.dao.MemberRepository;
import tripdream.common.dao.MemberTokenRepository;
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.entity.Member;
import tripdream.common.entity.MemberToken;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.util.JwtTokenProvider;
import tripdream.common.vo.login.LoginToken;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberTokenRepository memberTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public Member login(LoginRequest loginRequest) {

        log.info("create authentication object");

        // 1. login email/pw를 기반으로 Authentication 객체 생성
        // Authentication 에서 현재 인증 여부를 확인하는 authenticated 값 = false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        log.info("password check");

        // 2. 실제 검증(사용자 비밀번호 체크)가 이루어지는 부분
        // authenticate 메서드가 실행될 때 loadUserByUsername 메서드가 실행
        Authentication authentication =
                authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        log.info("making user token");

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        LoginToken loginToken = jwtTokenProvider.generateToken(authentication);

        log.info("login token info = {}", loginToken.getAccessToken());

        // 이메일에 해당되는 사용자
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).get();

        loginToken.giveMemberId(member.getId());

        MemberToken memberToken = new MemberToken(loginToken);
        memberTokenRepository.save(memberToken);
        member.changeMemberToken(memberToken);

        return member;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("call user method");

        UserDetails userDetails = memberRepository.findByEmail(username)
                    .map(this::createUserDetails)
                    .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        log.info("after call user method");

        return userDetails;
    }

    private UserDetails createUserDetails(Member member) {
        log.info("create user object");
        return User.builder()
                    .username(member.getEmail())
                    .password(passwordEncoder.encode(member.getPassword()))
                    .roles(member.getRoles().toArray(new String[0]))
                    .build();
    }
}
