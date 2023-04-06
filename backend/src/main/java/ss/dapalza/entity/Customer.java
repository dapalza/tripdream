package ss.dapalza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ss.dapalza.dto.req.RegisterRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_no")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String no;

    @Column(name = "customer_email")
    private String email;

    @Column(name = "customer_pw")
    private String password;

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
        this.email = req.getEmail();
        this.password = h_pw;
        this.nick = req.getUsername();
        this.dob = req.getDob();
        this.height = req.getHeight();
        this.feet = req.getFeet();
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