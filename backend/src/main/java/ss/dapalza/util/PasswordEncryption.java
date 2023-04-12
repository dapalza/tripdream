package ss.dapalza.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PasswordEncryption{
    private final PasswordEncoder passwordEncoder;
    
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password, String ex_pw) {
        return passwordEncoder.matches(password, ex_pw);
    }
}
