package wanted.subject.company.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.company.dto.CompanyRequestDto;
import wanted.subject.company.entity.Company;
import wanted.subject.company.repository.CompanyRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void clean(){
        companyRepository.deleteAll();
    }

    @Test
    public void 회사생성 () {
        // given
        CompanyRequestDto companyRequestDto = CompanyRequestDto.builder().name("원티드랩").build();
        // when
        Company company = companyService.createCompany(companyRequestDto);
        // then
        Assertions.assertThat("원티드랩").isEqualTo(company.getName());
    }

    @Test
    public void 회사확인 () {
        // given
        CompanyRequestDto companyRequestDto = CompanyRequestDto.builder().name("원티드랩").build();
        Company company1 = companyService.createCompany(companyRequestDto);
        // when
        Company company2 = companyService.verifiedCompany(company1.getId());
        // then
        Assertions.assertThat(company1).isSameAs(company2);
    }

    @Test
    public void 회사확인실패 () {
        // given
        CompanyRequestDto companyRequestDto = CompanyRequestDto.builder().name("원티드랩").build();
        Company company = companyService.createCompany(companyRequestDto);
        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> companyService.verifiedCompany(company.getId() + 1L));
        String errorMessage = e.getMessage();
        // then
        assertTrue(errorMessage.contains("회사 id 가 유효하지 않습니다."));
    }

}