package tripdream.member.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tripdream.common.dto.req.MemberDataChangeRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/my-page")
public class MyPageController {

    private final MyPageService myPageService;

    public ResponseEntity changeMemberData(@RequestBody @Validated MemberDataChangeRequest request) {
        return null;
    }

}
