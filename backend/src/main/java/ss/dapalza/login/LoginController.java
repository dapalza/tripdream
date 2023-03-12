package ss.dapalza.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ss.dapalza.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

    private LoginRepository customer;

    public LoginController(LoginRepository customer) {
        this.customer = customer;
    }

    @RequestMapping("/")
    public String login() {
        log.info("로그인 화면입니다 ");
        return "login";
    }

    @PostMapping("/signIn")
    public String signIn(String customer_email, String customer_pw) {
        log.info("id : {} , pw : {}", customer_email, customer_pw);
        Customer customer = this.customer.findCustomer(customer_email, customer_pw);
        if(customer != null) {
            return "loginOK";
        }
        return "loginFail";
    }
}
