package ss.dapalza.login;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.dapalza.entity.Customer;

public interface LoginRepository extends JpaRepository<Customer, String> {

    Customer findByEmail(String email);

    

//    Customer findCustomer(String customerEmail, String customerPw);
}
