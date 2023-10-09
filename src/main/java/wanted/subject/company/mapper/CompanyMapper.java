package wanted.subject.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.subject.company.dto.CompanyResponseDto;
import wanted.subject.company.entity.Company;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    // Company -> CompanyResponseDTO
    CompanyResponseDto companyToCompanyResponseDTO(Company company);

    // CompanyRequestDTO -> Company
    Company companyRequestDtoToCompany(Company CompanyRequestDto);
}
