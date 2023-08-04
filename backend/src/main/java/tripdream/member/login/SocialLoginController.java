package tripdream.member.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
//https://ed97-114-207-58-5.jp.ngrok.io/api/register


@RequiredArgsConstructor
@RestController
public class SocialLoginController {

    @Autowired
    SocialLoginService slservice;

    @GetMapping("/kakao-login")
    public String signInRest(@RequestParam(value="code") String code,HttpServletResponse response) throws Exception {
        System.out.println(code);
        
        String tmp = slservice.getAccessToken(code);
        System.out.println(tmp);
        String redirect_uri = "http://localhost:8081";
        
        
        System.out.println("==========================================");


        String reqURL = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(reqURL);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        HttpHeaders headers = new HttpHeaders();
        
        headers.set("Content-Type","application/x-www-form-urlencoded");
        headers.set("Authorization","Bearer"+tmp);

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        conn.setRequestProperty("Authorization","Bearer"+tmp);
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        // StringBuilder sb = new StringBuilder();
        
        System.out.println("==========================================");
        System.out.println("==========================================");
        
        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println("response body : " + result);
            
        response.sendRedirect(redirect_uri);
        
        return "loginFail";
    }
}
