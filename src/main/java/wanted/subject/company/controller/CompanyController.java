package wanted.subject.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.subject.company.dto.CompanyRequestDto;
import wanted.subject.company.dto.CompanyResponseDto;
import wanted.subject.company.entity.Company;
import wanted.subject.company.mapper.CompanyMapper;
import wanted.subject.company.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companys")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    /**
     * company 생성
     * @param companyRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity createCompany(@Valid @RequestBody CompanyRequestDto companyRequestDto) {
        // company
        Company company = companyService.createCompany(companyRequestDto);
        // response
        CompanyResponseDto response = companyMapper.companyToCompanyResponseDTO(company);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
