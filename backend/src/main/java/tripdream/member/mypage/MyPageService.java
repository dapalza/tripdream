package tripdream.member.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.entity.Member;
import tripdream.common.entity.S3File;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.file.FileService;
import tripdream.common.repository.MemberRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;

    private final FileService fileService;

    public S3File saveImageToMember(MultipartFile multipartFile, String nickname) throws IOException {
        Member member = memberRepository
                .findByNickname(nickname)
                .orElseThrow(MemberNotFoundException::new);

        S3File s3File = fileService.uploadImage(multipartFile);

        member.changeS3File(s3File);

        return s3File;
    }

}
