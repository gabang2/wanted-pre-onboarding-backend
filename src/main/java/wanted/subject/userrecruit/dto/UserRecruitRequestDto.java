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
    private Long userId;

    @Min(1)
    private Long recruitId;
}