package wanted.subject.recruit.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitResponseDto {
    private String country;         // 국가
    private String region;          // 지역
    private String position;        // 채용 포지션
    private int reward;             // 채용 보상금
    private String tech;            // 사용 기술
    private String content;         // 채용 내용
}
