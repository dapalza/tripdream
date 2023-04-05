package ss.dapalza.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ss.dapalza.entity.Customer;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String no;

    private String nick;

    private String dob;

    private int height;

    private int feet;
    //가입일이 몇년 되면 이벤트
    private LocalDateTime regdate;  //가입일
    //비밀번호 갱신 3개월이 지나면 알람
    private LocalDateTime updatedate;  //비밀번호 수정일
    //아래 부분은 로그인시 초기화
    // private LocalDateTime resigndate;   //마지막 로그인 일


    public LoginResponse(Customer customer) {
        this.no         = customer.getNo();
        this.nick       = customer.getNick();
        this.dob        = customer.getDob();
        this.height     = customer.getHeight();
        this.feet       = customer.getFeet();
        this.regdate    = customer.getRegdate();
        this.updatedate = customer.getUpdatedate();

    }
    
}
