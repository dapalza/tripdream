package tripdream.common.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DuplicateCheckRequest {
    private String email;

    private String nickname;
}
