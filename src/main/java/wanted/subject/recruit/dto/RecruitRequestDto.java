package wanted.subject.recruit.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitRequestDto {
    @NotNull
    private String country;         // 국가
    @NotNull
    private String region;          // 지역
    @NotNull
    private String position;        // 채용 포지션
    @NotNull
    private int reward;             // 채용 보상금
    @NotNull
    private String tech;            // 사용 기술
    @NotNull
    private String content;         // 채용 내용
}