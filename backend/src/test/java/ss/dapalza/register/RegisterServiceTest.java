package ss.dapalza.register;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {

    @Autowired
    private RegisterRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void registerCustomer() {

    }

    @Test
    void hashPassword() {
    }

    @Test
    void checkPassword() {
    }
}