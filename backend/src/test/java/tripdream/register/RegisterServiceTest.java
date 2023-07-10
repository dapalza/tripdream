package tripdream.register;

// import org.assertj.core.api.Assertions;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import tripdream.common.entity.Image;
import tripdream.common.entity.Member;
import tripdream.common.entity.Token;

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
                false,
                "layton",
                null,
                new Token(),
                new Image(),
                new ArrayList<>()
        );

        // when

        String password = member.getPassword();
        String encode = passwordEncoder.encode(password);

        // then

        Assertions.assertThat(passwordEncoder.matches(password, encode)).isEqualTo(true);
    }



}