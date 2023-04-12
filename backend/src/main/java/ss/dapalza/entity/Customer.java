package ss.dapalza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;
import ss.dapalza.dto.req.RegisterRequest;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank
    private String email;

    @Column(name = "customer_pw")
    @NotBlank
    private String password;

    @Column(name = "customer_nick")
    // @NotBlank
    private String nick;

    @Column(name = "customer_dob")
    // @NotBlank
    private String dob;

    @Column(name = "customer_height")
    // @NotNull
    // @Range(min = 50, max = 300)
    private int height;

    @Column(name = "customer_feet")
    // @NotNull
    // @Range(min = 1, max = 500)
    private int feet;

    @Column(name = "customer_regdate")
    // @NotNull
    private LocalDateTime regdate;

    @Column(name = "customer_updatedate")
    // @NotNull
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