package ss.dapalza.login;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ss.dapalza.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService{
    @Autowired
    private final LoginRepository loginRepository;

    public Customer login(Customer customer){

        Customer findCustomer = loginRepository.findByEmail(customer.getEmail());

        if(findCustomer == null){
            System.out.println("해당 이메일을 가진 유저는 없습니다.");
            return null;
        }
        if(!checkPassword(customer.getPw(),findCustomer.getPw())){
            System.out.println("패스워드가 틀립니다.");
            return null;
        }

        


        return findCustomer;
    }

    private final PasswordEncoder passwordEncoder;
    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    public boolean checkPassword(String pw, String ex_pw) {
        return passwordEncoder.matches(pw, ex_pw);
    }
}
