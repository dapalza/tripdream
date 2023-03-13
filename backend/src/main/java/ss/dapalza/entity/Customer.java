package ss.dapalza.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ss.dapalza.dto.req.RegisterRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
//승화) setter도 향후 필요할 것 같아서 미리 추가해놓음
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_no")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String no;

    //승화) Json방식으로 받기 위해서는 Column이 아니라 JsonProperty로
    //승화) 값을 매칭해야 한다.
    @JsonProperty(value = "customer_email")
    private String email;

    @Column(name = "customer_pw")
    private String pw;

    @Column(name = "customer_nick")
    private String nick;

    @Column(name = "customer_dob")
    private String dob;

    @Column(name = "customer_height")
    private int height;

    @Column(name = "customer_feet")
    private int feet;

    @Column(name = "customer_regdate")
    private LocalDateTime regdate;

    @Column(name = "customer_updatedate")
    private LocalDateTime updatedate;

    @Column(name = "customer_resigndate")
    private LocalDateTime resigndate;

//    public Customer(RequestRequest req) {
//        email = req.getEmail();
//        pw = req.getPw();
//        nick = req.getNick();
//        dob = req.getDob();
//        height = req.getHeight();
//        feet = req.getFeet();
//    }

    @PrePersist
    private void setCustomerData() {
        this.regdate = LocalDateTime.now();
        this.no = regdate.format(DateTimeFormatter.ofPattern("yyMMdd"));
        this.resigndate = null;
    }

    @PreUpdate
    private void setUpdatedate() {
        this.updatedate = LocalDateTime.now();
    }
}