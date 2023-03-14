package ss.dapalza.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Expw {

    @OneToOne(mappedBy = "expw")
    private Customer customer;

    @Id
    private String code;

    private String ex_pw;

    @PrePersist
    void setCode() {
        this.code = customer.getNo();
    }

    public Expw(String ex_pw, Customer customer) {
        this.ex_pw = ex_pw;
        this.customer = customer;
    }
}
