package tripdream.member.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.dto.req.LoginRequest;
import tripdream.common.dto.req.MemberDataChangeRequest;
import tripdream.common.dto.req.PasswordChangeRequest;
import tripdream.common.dto.res.MemberResponse;
import tripdream.common.entity.Member;
import tripdream.common.entity.S3File;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.exception.PasswordIncorrectException;
import tripdream.common.file.FileService;
import tripdream.common.repository.MemberRepository;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;

    private final FileService fileService;

    private final PasswordEncoder passwordEncoder;

    public S3File saveImageToMember(MultipartFile multipartFile, String nickname) throws IOException {
        Member member = memberRepository
                .findByNickname(nickname)
                .orElseThrow(MemberNotFoundException::new);

        S3File s3File = fileService.uploadImage(multipartFile);

        member.changeS3File(s3File);

        return s3File;
    }

    @Transactional
    public MemberResponse changeMemberData(MemberDataChangeRequest changeRequest) {

        Member member = memberRepository.findByEmail(changeRequest.getEmail()).orElseThrow(
                MemberNotFoundException::new
        );

        MemberResponse response = new MemberResponse(member.changeMemberData(changeRequest));

        return response;
    }

    public void changePassword(LoginRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        member.changePassword(hashedPassword);

    }

    @Transactional
    public void changeOldPassword(PasswordChangeRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        if(!passwordEncoder.matches(request.getOldPassword(), member.getPassword())) {
            throw new PasswordIncorrectException();
        }
        String hashedPassword = passwordEncoder.encode(request.getNewPassword());

        member.changePassword(hashedPassword);

    }

}
