package ss.dapalza.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ss.dapalza.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//https://ed97-114-207-58-5.jp.ngrok.io/api/register


@RequiredArgsConstructor
@Slf4j
@RestController
public class SocialLoginController {

    @Autowired
    SocialLoginService slservice;

    @GetMapping("/kakao-login")
    public String signInRest(@RequestParam(value="code") String code) throws Exception {
        System.out.println(code);
        
        String tmp = slservice.getAccessToken(code);
        System.out.println(tmp);
        return "loginFail";
    }
}
