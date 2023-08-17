package tripdream.common.dto.req;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PasswordChangeRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
