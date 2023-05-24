package tripdream.login;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import tripdream.common.exception.ErrorCode;
import tripdream.common.exception.MemberNotFoundException;
import tripdream.common.exception.PasswordIncorrectException;
import tripdream.common.exception.ValidCheckException;
import tripdream.dto.login.LoginToken;
import tripdream.dto.req.LoginRequest;
import tripdream.entity.Member;

@Service
@RequiredArgsConstructor
public class LoginService{

    private final LoginRepository loginRepository;

    public Member login(LoginRequest loginRequest, BindingResult bindingResult) throws ValidCheckException {

        Member member = loginRepository.findByEmail(loginRequest.getEmail());

        // 이메일에 해당되는 사용자 없음
        if(member == null){
            throw new MemberNotFoundException(bindingResult, ErrorCode.MEMBER_NOT_FOUND);
        }
        // 비밀번호가 틀림
        else if(!checkPassword(loginRequest.getPassword(), member.getPassword())){
            throw new PasswordIncorrectException(bindingResult, ErrorCode.PASSWORD_INCORRECT);
        }

        LoginToken loginToken = new LoginToken();
        member.setMemberToken(loginToken);

        return member;
    }

    private final PasswordEncoder passwordEncoder;
    public String hashPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    public boolean checkPassword(String pw, String ex_pw) {
        return passwordEncoder.matches(pw, ex_pw);
    }

}
