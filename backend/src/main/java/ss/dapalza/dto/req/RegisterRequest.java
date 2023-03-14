package ss.dapalza.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;

    private String password;

    private String username;

    private String dob;

    private int height;

    private int feet;
}
