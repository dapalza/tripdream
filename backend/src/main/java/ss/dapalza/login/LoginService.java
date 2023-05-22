package ss.dapalza.login;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ss.dapalza.common.exception.ErrorCode;
import ss.dapalza.common.exception.MemberNotFoundException;
import ss.dapalza.common.exception.PasswordIncorrectException;
import ss.dapalza.common.exception.ValidCheckException;
import ss.dapalza.dto.login.LoginToken;
import ss.dapalza.dto.req.LoginRequest;
import ss.dapalza.entity.DPZToken;
import ss.dapalza.entity.Member;

@Service
@RequiredArgsConstructor
public class LoginService{

    private final TokenRepository tokenRep;
    private final LoginRepository loginRepository;

    public Member login(LoginRequest loginRequest, BindingResult bindingResult) throws ValidCheckException {

        Member member = loginRepository.findByEmail(loginRequest.getEmail());

        if(member == null){
            throw new MemberNotFoundException(bindingResult, ErrorCode.MEMBER_NOT_FOUND);
        }
        if(!checkPassword(loginRequest.getPassword(),member.getPassword())){
            throw new PasswordIncorrectException(bindingResult, ErrorCode.PASSWORD_INCORRECT);
        }
        return member;
    }

    private final PasswordEncoder passwordEncoder;
    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    public boolean checkPassword(String pw, String ex_pw) {
        return passwordEncoder.matches(pw, ex_pw);
    }

    public DPZToken makeToken(String customerNo, LoginToken lt) {
        DPZToken result = new DPZToken(customerNo,lt);
        result = tokenRep.save(result);
        return result;
    }
}
