package ss.dapalza.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ss.dapalza.entity.Customer;
import ss.dapalza.util.TimeControl;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoginEvent {
    private boolean birthEvent;     // 생일인 달일 경우
    private boolean updateEvent;  //6개월간 같은 비밀번호 사용
    private int regdateEvent; //회원가입한지 1주년 기념일일 경우 년을 표시 아닐 경우 -1 하루가 아닌 월 단위기에 해당 부분은 확인이 필요

    public LoginEvent(Customer customer){
        this.birthEvent = BirthEvent(customer.getDob());
        this.updateEvent = UpdateEvent(customer.getUpdatedate());
        this.regdateEvent = regdateEvent(customer.getRegdate());
    }
    private boolean BirthEvent(String dob) {
        if (new TimeControl().BirthBNA(dob)==0) {
            System.out.println("오늘은 생일입니다.");
            return true;
        } else {
            System.out.println("오늘은 생일이 아닙니다.");
            return false;
        }
    }
    
    private boolean UpdateEvent(LocalDateTime updatedate) {
        if( new TimeControl().GapMonths(updatedate)>6){
            System.out.println("비밀번호를 변경한지 6개월이 지났습니다. 변경이 필요합니다.");
            return true;
        }else {
            System.out.println("최근 6개월 내에 비밀번호를 변경하였습니다.");
            return false;
        }
        
    }
    
    private int regdateEvent(LocalDateTime regdate) {
        int tmp = new TimeControl().GapMonths(regdate);
        
        System.out.println(tmp+"개월 동안 애용 중");
        return tmp%12==0?tmp/12:-1;
    }
}
