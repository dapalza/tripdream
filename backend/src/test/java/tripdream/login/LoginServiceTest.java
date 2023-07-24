package tripdream.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import tripdream.common.config.SpringSecurityAuditorAwareConfig;
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.entity.Member;
import tripdream.common.entity.Token;
import tripdream.common.repository.MemberRepository;
import tripdream.common.repository.TokenRepository;
import tripdream.common.util.JwtTokenProvider;

import java.time.LocalDate;

@SpringBootTest
class LoginServiceTest{

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired SpringSecurityAuditorAwareConfig helper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("login test")
    void login() {

        // given
        String hashedPassword = passwordEncoder.encode("abcde");
        Member oldMember = Member
                .builder()
                .email("abc@email.com")
                .password(hashedPassword)
                .nickname("nicknameA")
                .birth(LocalDate.of(1998, 02, 24))
                .build();

        memberRepository.save(oldMember);

        LoginRequest loginRequest =
                new LoginRequest(
                        "abc@email.com",
                        "abcde"
                );

        Authentication authentication = getAuthentication(loginRequest);

        // when


        // 인증 정보를 기반으로 JWT 토큰 생성
        Token token = jwtTokenProvider.generateToken(authentication);

        String email = loginRequest.getEmail();

        // 이메일에 해당되는 사용자
        Member member = saveTokenToMember(token, email);


        // then
        Assertions.assertThat(member.getEmail()).isEqualTo(loginRequest.getEmail());

    }

    private Member saveTokenToMember(Token token, String email) {
        Member member = memberRepository.findByEmail(email).get();

        member.changeMemberToken(token);
        return member;
    }

    private Authentication getAuthentication(LoginRequest loginRequest) {
        // 1. login email/pw를 기반으로 Authentication 객체 생성
        // Authentication 에서 현재 인증 여부를 확인하는 authenticated 값 = false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());


        // 2. 실제 검증(사용자 비밀번호 체크)가 이루어지는 부분
        // authenticate 메서드가 실행될 때 loadUserByUsername 메서드가 실행
        return authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);
    }

}
