package ss.dapalza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ss.dapalza.dto.req.RegisterRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_no")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String no;

    //승화) Json방식으로 받기 위해서는 Column이 아니라 JsonProperty로
    //승화) 값을 매칭해야 한다.
    @Column(name = "customer_email")
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

    @OneToOne
    @JoinColumn(name = "code")
    private Expw expw;

    public Customer(RegisterRequest req, String h_pw) {
        email = req.getEmail();
        pw = h_pw;
        nick = req.getUsername();
        dob = req.getDob();
        height = req.getHeight();
        feet = req.getFeet();
    }

    @PrePersist
    private void setCustomerData() {
        this.regdate = LocalDateTime.now();
        this.updatedate = LocalDateTime.now();
        this.resigndate = null;
    }

    @PreUpdate
    private void setUpdatedate() {
        this.updatedate = LocalDateTime.now();
    }
}