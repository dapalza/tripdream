package tripdream.register;

// import org.assertj.core.api.Assertions;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import tripdream.common.entity.Image;
import tripdream.common.entity.Member;
import tripdream.common.entity.Token;
import tripdream.common.repository.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;


@SpringBootTest
class RegisterServiceTest{

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("register test")
    void register() {
        //given
        String hashedPassword = passwordEncoder.encode("abcde");
        Member member = Member
                .builder()
                .email("abc@email.com")
                .password(hashedPassword)
                .nickname("nicknameA")
                .birth(LocalDate.of(1998, 02, 24))
                .build();

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Assertions.assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());

    }

    @Test
    @DisplayName("hash test")
    void passwordTest() {
        //given
        String rawPassword = "abcde";
        String hashedPassword = passwordEncoder.encode(rawPassword);

        //when
        boolean result = passwordEncoder.matches(rawPassword, hashedPassword);

        //then
        Assertions.assertThat(result).isTrue();
    }

}