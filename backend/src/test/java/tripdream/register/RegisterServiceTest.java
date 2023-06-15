package tripdream.register;

// import org.assertj.core.api.Assertions;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import tripdream.common.dao.MemberRepository;
import tripdream.common.dao.MemberTokenRepository;
import tripdream.common.entity.Member;
import tripdream.common.entity.MemberToken;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.util.JwtTokenProvider;
import tripdream.common.vo.login.LoginToken;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;


@SpringBootTest
class RegisterServiceTest{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void hashPassword() {
        // given
        Member member = new Member(
                UUID.randomUUID().toString(),
                "abc@email.com",
                "abcde",
                "F",
                LocalDate.of(1998, 2, 24),
                "0",
                "layton",
                null,
                new MemberToken(),
                new ArrayList<>()
        );

        // when

        String password = member.getPassword();
        String encode = passwordEncoder.encode(password);

        // then

        Assertions.assertThat(passwordEncoder.matches(password, encode)).isEqualTo(true);
    }



}