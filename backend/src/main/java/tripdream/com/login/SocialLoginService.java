package tripdream.com.login;

import java.util.Base64;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.gdj51.MyTodo.web.dao.IACDao;

@Service
public class SocialLoginService {
        
    public String getAccessToken(String authorize_code)throws Exception{
        String access_Token = "";
        String refresh_Token = "";
        String id_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");

            sb.append("&client_id=998ea5ac18af79fb613db9e18647988f"); // REST_API키 본인이 발급받은 key 넣어주기
            sb.append("&client_secret=P6laJcnmFhJD4xiA2ZeRFZpMA2IOTpou"); // REST_API키 본인이 발급받은 key 넣어주기
            sb.append("&redirect_uri=http://localhost:8088/kakao-login"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // jackson objectmapper 객체 생성
            ObjectMapper objectMapper = new ObjectMapper();
            // JSON String -> Map
            Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
            });
            
            access_Token = jsonMap.get("access_token").toString();
            refresh_Token = jsonMap.get("refresh_token").toString();
            id_Token = jsonMap.get("id_token").toString();
            
            br.close();
            bw.close();
            
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            System.out.println("id_token : " + id_Token);
            String[] splitToken = id_Token.split("\\.");
            
            //id_Token복호화 작업 필요
            if(splitToken.length!=3){
                System.out.println("token is something problem");
            }
            for(int i= 0;i<splitToken.length-1;i++){
                System.out.println("BeforeToken : "+ splitToken[i]);
                byte[] decodedBytes = Base64.getDecoder().decode(splitToken[i]);
                String decodedString = new String(decodedBytes,"utf-8");
                System.out.println("AfterToken : "+ decodedString);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }
}
