package wanted.subject.company.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDto {
    private Long id;        // 회사 id
    private String name;    // 회사 이름
}
