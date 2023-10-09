package wanted.subject.company.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompanyRequestDto {
    @NotNull
    private String name;
}
