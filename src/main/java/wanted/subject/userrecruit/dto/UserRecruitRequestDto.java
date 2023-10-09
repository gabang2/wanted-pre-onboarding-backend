package wanted.subject.userrecruit.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRecruitRequestDto {
    @Min(1)
    private Long userId;        // 사용자 id

    @Min(1)
    private Long recruitId;     // 채용 공고 id
}