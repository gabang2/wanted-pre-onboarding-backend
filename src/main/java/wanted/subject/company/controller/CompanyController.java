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
    @PostMapping
    public ResponseEntity createCompany(@Valid @RequestBody CompanyRequestDto companyRequestDto) {
        companyService.createCompany(companyRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
