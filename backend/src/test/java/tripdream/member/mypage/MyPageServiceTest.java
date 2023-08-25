package tripdream.member.mypage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import tripdream.common.dto.req.MemberDataChangeRequest;
import tripdream.common.entity.Gender;
import tripdream.common.entity.Member;
import tripdream.common.repository.MemberRepository;

import java.time.LocalDate;

@SpringBootTest
class MyPageServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void changeMemberData() {
        // given
        String hashedPassword = passwordEncoder.encode("abcde");
        Member member = Member
                .builder()
                .email("abc@email.com")
                .password(hashedPassword)
                .nickname("nicknameA")
                .birth(LocalDate.of(1998, 2, 24))
                .build();

        memberRepository.save(member);

        // when

        hashedPassword = passwordEncoder.encode("edcba");

        MemberDataChangeRequest changeRequest = MemberDataChangeRequest.builder()
                .email("abcdefg@email.com")
                .password(hashedPassword)
                .nickname("nicknameBBB")
                .birth(LocalDate.of(1998, 8, 3))
                .gender(Gender.F)
                .build();

        Member savedMember = memberRepository.findByNickname(member.getNickname()).orElseThrow();

        savedMember.changeMemberData(changeRequest);
        memberRepository.save(savedMember);

        // then

        Assertions.assertThat(savedMember.getNickname()).isEqualTo("nicknameBBB");
        Assertions.assertThat(savedMember.getNickname()).isNotEqualTo(member.getNickname());


    }

    @Test
    @DisplayName("password change")
    void changePassword() {
        // given
        String hashedPassword = passwordEncoder.encode("abcde");
        Member member = Member
                .builder()
                .email("abc@email.com")
                .password(hashedPassword)
                .nickname("nicknameA")
                .birth(LocalDate.of(1998, 2, 24))
                .build();

        memberRepository.save(member);

        // when

        hashedPassword = passwordEncoder.encode("edcba");

        Member savedMember = memberRepository.findByNickname(member.getNickname()).orElseThrow();

        savedMember.changePassword(hashedPassword);
        memberRepository.save(savedMember);


        // then

        Assertions.assertThat(passwordEncoder.matches("edcba", memberRepository.findByNickname(member.getNickname()).orElseThrow().getPassword())).isTrue();

    }


}