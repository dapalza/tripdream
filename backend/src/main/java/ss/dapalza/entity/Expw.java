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

    // 사용자 아이디
    @Id
    private String code;

    // 문자로 된 패스워드
    private String c_pw;

    @PrePersist
    void setCode() {
        this.code = customer.getNo();
    }

    public Expw(String c_pw, Customer customer) {
        this.c_pw = c_pw;
        this.customer = customer;
    }
}
