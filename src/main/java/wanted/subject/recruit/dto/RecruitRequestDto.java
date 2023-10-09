package wanted.subject.recruit.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitRequestDto {
    @Min(1)
    private Long companyId;
    @NotNull
    private String country;         // 국가
    @NotNull
    private String region;          // 지역
    @NotNull
    private String position;        // 채용 포지션
    @Min(0)
    private int reward;             // 채용 보상금
    @NotNull
    private String tech;            // 사용 기술
    @NotNull
    private String content;         // 채용 내용
}