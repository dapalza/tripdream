package tripdream.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import tripdream.common.dao.MemberRepository;
import tripdream.common.dao.MemberTokenRepository;
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.entity.Member;
import tripdream.common.entity.MemberToken;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.util.JwtTokenProvider;
import tripdream.common.vo.login.LoginToken;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberTokenRepository memberTokenRepository;
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    void login() {
        // given

        LoginRequest loginRequest =
                new LoginRequest(
                        "abc@email.com",
                        "abcde",
                        null,
                        null
                );
        // when

        // 1. login email/pw를 기반으로 Authentication 객체 생성
        // Authentication 에서 현재 인증 여부를 확인하는 authenticated 값 = false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());


        // 2. 실제 검증(사용자 비밀번호 체크)가 이루어지는 부분
        // authenticate 메서드가 실행될 때 loadUserByUsername 메서드가 실행
        Authentication authentication =
                authenticationManagerBuilder
                        .getObject()
                        .authenticate(authenticationToken);


        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        LoginToken loginToken = jwtTokenProvider.generateToken(authentication);


        // 이메일에 해당되는 사용자
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).get();

        loginToken.giveMemberId(member.getId());

        MemberToken memberToken = new MemberToken(loginToken);
        memberTokenRepository.save(memberToken);
        member.changeMemberToken(memberToken);

        // then

    }




    // 등록된 사용자 정보 탐색 (오버라이드)
    // .authenticate() 메소드 실행 시 해당 메소드가 실행됨.
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails userDetails = memberRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return userDetails;
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}