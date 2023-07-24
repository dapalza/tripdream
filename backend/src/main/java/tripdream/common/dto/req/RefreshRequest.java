package tripdream.common.dto.req;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RefreshRequest {

    @NotNull
    @NotBlank
    private String refreshToken;
}
