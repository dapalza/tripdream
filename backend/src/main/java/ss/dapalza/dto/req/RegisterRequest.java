package ss.dapalza.dto.req;

import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;

    private String password;

    private String username;

    private String dob;

    private int height;

    private int feet;


    // @Builder
    // public RegisterRequest(String email, String password, String username, String dob, int height, int feet) {
    //     this.email = email;
    //     this.password = password;
    //     this.username = username;
    //     this.dob = dob;
    //     this.height = height;
    //     this.feet = feet;
    // }
}
