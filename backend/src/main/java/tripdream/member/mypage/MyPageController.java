package tripdream.member.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tripdream.common.dto.req.ImageRequest;
import tripdream.common.dto.req.MemberDataChangeRequest;
import tripdream.common.dto.req.PasswordChangeRequest;
import tripdream.common.dto.res.ImageResponse;
import tripdream.common.dto.res.MemberResponse;
import tripdream.common.entity.S3File;
import tripdream.common.file.FileService;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/my-page")
public class MyPageController {

    private final MyPageService myPageService;

    private final FileService fileService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/upload-image")
    public ResponseEntity<ImageResponse> uploadImage(@RequestPart MultipartFile file, @RequestPart ImageRequest imageRequest) throws IOException {

        S3File s3File = myPageService.saveImageToMember(file, imageRequest.getNickname());

        ImageResponse imageResponse = new ImageResponse(s3File.getFullPath());

        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/change/member-data")
    public ResponseEntity<MemberResponse> changeMemberData(@RequestBody @Validated MemberDataChangeRequest request) {
        MemberResponse memberResponse = myPageService.changeMemberData(request);

        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @PostMapping("/change/password")
    public ResponseEntity<String> changeOldPassword(@RequestBody @Validated PasswordChangeRequest request) {
        myPageService.changeOldPassword(request);

        return new ResponseEntity<>("password changed", HttpStatus.OK);
    }

}
