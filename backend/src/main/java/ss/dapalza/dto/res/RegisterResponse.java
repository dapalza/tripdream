package ss.dapalza.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ss.dapalza.entity.Customer;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RegisterResponse {
    private String no;

    private String email;

    private String password;

    private String nick;

    private String dob;

    private int height;

    private int feet;

    private LocalDateTime regdate;

    private LocalDateTime updatedate;

    private LocalDateTime resigndate;

    public RegisterResponse(Customer customer) {
        this.no = customer.getNo();;
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.nick = customer.getNick();
        this.dob = customer.getDob();
        this.height = customer.getHeight();
        this.feet = customer.getFeet();
        this.regdate = customer.getRegdate();
        this.updatedate = customer.getUpdatedate();
        this.resigndate = customer.getResigndate();
    }
}
