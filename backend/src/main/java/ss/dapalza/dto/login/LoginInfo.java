package ss.dapalza.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ss.dapalza.entity.Customer;
import ss.dapalza.util.TimeControl;


@Getter
@AllArgsConstructor
public class LoginInfo {
    
    private String nick;
    private int height;
    private int feet;
    private int age;

    public LoginInfo(Customer customer){
        this.nick = customer.getNick();
        this.height = customer.getHeight();
        this.feet = customer.getFeet();
        this.age = getCustomerAge(customer.getDob());
    }

    private int getCustomerAge(String dob) {
        return new TimeControl().GetAge(dob);
    }

}
