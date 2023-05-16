package ss.dapalza.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @ApiModelProperty(value = "abc@email.com")
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
