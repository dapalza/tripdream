package tripdream.com.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class TimeControl {
    public TimeControl(){}
    public LocalDate MakeLocalDate(String input){return LocalDate.parse(input);}
    public int GapMonths(LocalDateTime input){return (int)ChronoUnit.MONTHS.between(input,LocalDateTime.now());}
    public int BirthBNA(String dob){
        LocalDate birthDate = MakeLocalDate(dob);  //사용자 생일
        if(birthDate.getDayOfMonth()==29&&birthDate.getMonthValue()==2){
            if(!IsLeapYear(LocalDate.now().getYear())){ //만약 2월 29일 생인데 올해가 윤년이 아닐 경우
                birthDate = LocalDate.of(birthDate.getYear(),3,1);
            }
        }
        int month = birthDate.getMonthValue() - LocalDateTime.now().getMonthValue();;
        if(month!=0){
            return month; //음수면 After, 양수면 Before
        }
        int day = birthDate.getDayOfMonth() - LocalDateTime.now().getDayOfMonth();
        if(day == 0){
            return 0; // 오늘 생일
        }
        return day;
    }
    private boolean IsLeapYear(int year) {
        return Year.isLeap(year);
    }
    public int GetAge(String dob){
        LocalDate LD = MakeLocalDate(dob);
        int age = LocalDateTime.now().getYear()-LD.getYear();
        age += BirthBNA(dob) <=0 ? 0: -1;
        return age;
    }
}
