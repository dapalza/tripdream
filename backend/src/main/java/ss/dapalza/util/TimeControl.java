package ss.dapalza.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeControl {
    public TimeControl(){}
    public int parseMonth(LocalDateTime input){return input.toLocalDate().getMonthValue();}
    public int parseDay(LocalDateTime input){return input.toLocalDate().getDayOfMonth();}
    public int parseYear(LocalDateTime input){return input.toLocalDate().getYear();}
    public int parseMonth(String input){return LocalDate.parse(input).getMonthValue();}
    public int parseDay(String input){return LocalDate.parse(input).getDayOfMonth();}
    public int parseYear(String input){return LocalDate.parse(input).getYear();}
    public int gapMonths(LocalDateTime input){return (int)ChronoUnit.MONTHS.between(input,LocalDateTime.now());}
    public int birthBNA(String dob){
        int month = parseMonth(dob) - parseMonth(LocalDateTime.now());
        if(month!=0){
            return month; //음수면 After, 양수면 Before
        }
        int day = parseDay(dob) - parseDay(LocalDateTime.now());
        if(day == 0){
            return 0; // 오늘 생일
        }
        return day;
    }
    public int getAge(String dob){
        int age = parseYear(LocalDateTime.now())-parseYear(dob);
        age += birthBNA(dob) <=0 ? 0: -1;
        return age;
    }
}
