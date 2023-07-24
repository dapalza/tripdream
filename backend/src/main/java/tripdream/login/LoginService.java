package tripdream.login;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripdream.common.config.SpringSecurityAuditorAwareConfig;
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.entity.Member;
import tripdream.common.entity.Token;
import tripdream.common.exception.DifferentIpException;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.repository.MemberRepository;
import tripdream.common.repository.TokenRepository;
import tripdream.common.util.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    private final SpringSecurityAuditorAwareConfig helper;

    public Member login(LoginRequest loginRequest) {

        Authentication authentication = getAuthentication(loginRequest);

        log.info("start making user token");

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        Token token = jwtTokenProvider.generateToken(authentication);

        log.info("login token info = {}", token.getAccessToken());

        String email = loginRequest.getEmail();

        // 이메일에 해당되는 사용자
        return saveTokenToMember(token, email);

    }

    private Member saveTokenToMember(Token token, String email) {
        Member member = memberRepository.findByEmail(email).get();

        member.changeMemberToken(token);
        return member;
    }

    private Authentication getAuthentication(LoginRequest loginRequest) {
        log.info("create authentication object");

        // 1. login email/pw를 기반으로 Authentication 객체 생성
        // Authentication 에서 현재 인증 여부를 확인하는 authenticated 값 = false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        log.info("password checking");

        // 2. 실제 검증(사용자 비밀번호 체크)가 이루어지는 부분
        // authenticate 메서드가 실행될 때 loadUserByUsername 메서드가 실행
        return authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);
    }

    // 등록된 사용자 정보 탐색 (오버라이드)
    // .authenticate() 메소드 실행 시 해당 메소드가 실행됨.
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("call user method");

        UserDetails userDetails = memberRepository.findByEmail(username)
                    .map(this::createUserDetails)
                    .orElseThrow(MemberNotFoundException::new);

        log.info("after call user method");

        return userDetails;
    }

    public Token checkRefreshTokenValid(String refreshToken) {
        log.info("call find by refresh token");

        return tokenRepository.findByRefreshToken(refreshToken);
    }

    private UserDetails createUserDetails(Member member) {
        log.info("create user object");
        return User.builder()
                    .username(member.getEmail())
                    .password(member.getPassword())
                    .roles(member.getRoles().toArray(new String[0]))
                    .build();
    }

    public Member refreshAccessToken(Token token) {
        log.info("call refresh access token");

        Member member = memberRepository.findByToken(token).get();

        String createdByIp = token.getCreatedByIp();
        if(helper.getCurrentAuditor().get().equals(createdByIp)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token.getRefreshToken());
            // 인증 정보를 기반으로 JWT 토큰 생성
            Token newToken = jwtTokenProvider.regenerateAccessToken(authentication, token);

            log.info("refresh access token info = {}", newToken.getAccessToken());
            Member loginMember = saveTokenToMember(newToken, member.getEmail());

            log.info("refresh: loginMember token id = {}", loginMember.getToken().getAccessToken());
            log.info("refresh: loginMember id = {}", loginMember.getId());
            return loginMember;
        } else {
            throw new DifferentIpException();
        }

    }
}
