package ss.dapalza.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ss.dapalza.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RestController
public class LoginController {

    //승화) 어제 에러로 빨간 줄이 뜬 이유는 LoginRepository.java가 없어서 뜬 것으로
    //승화) 해당 파일 추가 후 에러가 해결됨
    private LoginRepository customer;

    public LoginController(LoginRepository customer) {
        this.customer = customer;
    }

    @RequestMapping("/")
    public String login() {
        log.info("로그인 화면입니다 login");
        return "login";
    }
    //승화) 이전 html form 방식 => 문제가 된 부분은 form에서 name이 기존 InputEmail으로 정의되어 매칭이 안됨
    //승화) name과 받는 변수의 이름이 같아야 받아짐
    //승화) 문제는 Json 형태로 받는 것이 아님... 이 방식으로 할 경우 front 부분과의 데이터 소통이 힘듬
    @PostMapping("/signIn")
    public String signIn(String customer_email, String customer_pw) {
        log.info("id : {} , pw : {}", customer_email, customer_pw);

        if(customer_email != null) {
            return "loginOK";
        }
        return "loginFail";
    }

    //승화) 해당 부분은 Json통신으로 받을 수 있게 한 부분
    //승화) Json형태로 Post를 보내주면 해당 부분의 값을 받아지나 Email만 받아짐 그 이유는
    //승화) Customer.java가 Column으로 받고 있어서 Json이 아니라 Key-Value 형태로 넘겨줘야
    //승화) 인식이 가능할 것으로 예상
    //승화) 현 Email만 Column => JsonProperty로 수정하여 받아옴
    //승화) JsonProperty는 Json뿐만 아니라 form 방식도 받을 수 있음.
    //승화) 그리고 개발시 html을 활용하기 보다는 Postman을 활용하여
    //승화) RestApi의 방식으로 만드는 것을 권장
    @PostMapping("/signInRest")
    public String signInRest(@RequestBody Customer customerT) {
        System.out.println(customerT.getEmail());

        Customer customer = new Customer();
//        Customer customer = this.customer.findCustomer(customer_email, customer_pw);
        if(customer != null) {
            return "loginOK";
        }
        return "loginFail";
    }
}
