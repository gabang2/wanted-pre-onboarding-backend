package wanted.subject.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;        // 사용자 id
    private String name;    // 사용자 이름
}
