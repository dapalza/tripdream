package ss.dapalza.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Customer {

    @Id
    @Column(name = "customer_no")
    private String no;

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
}
