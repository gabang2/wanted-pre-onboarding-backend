package wanted.subject.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.company.dto.CompanyRequestDto;
import wanted.subject.company.entity.Company;
import wanted.subject.company.mapper.CompanyMapper;
import wanted.subject.company.repository.CompanyRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    /**
     * CREATE : 생성
     *
     * @param companyRequestDto
     */
    public Company createCompany(CompanyRequestDto companyRequestDto) {

        Company company = Company.builder().name(companyRequestDto.getName()).build();  // company 생성

        return companyRepository.save(company);
    }

}
