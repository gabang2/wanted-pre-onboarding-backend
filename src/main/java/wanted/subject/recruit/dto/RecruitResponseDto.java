package wanted.subject.recruit.dto;

import lombok.*;
import wanted.subject.company.dto.CompanyResponseDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitResponseDto {
    private Long id;                // 채용 공고 id
    private String companyName;     // 회사명
    private String country;         // 국가
    private String region;          // 지역
    private String position;        // 채용 포지션
    private int reward;             // 채용 보상금
    private String tech;            // 사용 기술
    private String content;         // 채용 내용
}
