package wanted.subject.company.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {
    @NotNull
    private String name;    // 회사 이름
}