package ss.dapalza.register;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ss.dapalza.common.security.WebSecurityConfig;
import ss.dapalza.dto.req.RegisterRequest;
import ss.dapalza.dto.res.RegisterResponse;
import ss.dapalza.entity.Customer;
import ss.dapalza.entity.Expw;

@SpringBootTest
class RegisterServiceTest {

    @Autowired
    private RegisterService service;

    @Autowired
    private RegisterRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeEach(){
        WebSecurityConfig config = new WebSecurityConfig();
    }

    @Test
    void registerCustomer() {
        // given

        // when
        RegisterRequest request = RegisterRequest.builder()
                .username("yoon")
                .email("abc@abc.com")
                .feet(235)
                .height(165)
                .dob("98/02/24")
                .password("abcdefg")
                .build();

//        Customer customer = service.registerCustomer(request);
//        RegisterResponse res = new RegisterResponse(customer);
//
//        String h_pw = res.getPw();
//        Customer customer01 = new Customer(request, h_pw);
//        Expw expw = customer01.getExpw();
//        String c_pw = expw.getC_pw();

        String decode = request.getPassword();
        String encode = passwordEncoder.encode(request.getPassword());

        // then
        Assertions.assertThat(true).isEqualTo(passwordEncoder.matches(decode, encode));
        Assertions.assertThat(decode).isEqualTo(request.getPassword());
    }

    @Test
    void hashPassword() {
    }

    @Test
    void checkPassword() {
    }
}