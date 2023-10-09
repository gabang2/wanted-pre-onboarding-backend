package wanted.subject.userrecruit.dto;

import lombok.*;
import wanted.subject.recruit.dto.RecruitResponseDto;
import wanted.subject.user.dto.UserResponseDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRecruitResponseDto {
    private UserResponseDto user;
    private RecruitResponseDto recruit;
}
