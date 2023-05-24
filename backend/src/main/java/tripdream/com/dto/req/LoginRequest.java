package tripdream.com.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @ApiModelProperty(value = "abc@email.com")
    @NotNull
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
