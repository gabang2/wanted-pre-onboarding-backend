package wanted.subject.recruit.service;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.company.dto.CompanyRequestDto;
import wanted.subject.company.entity.Company;
import wanted.subject.company.repository.CompanyRepository;
import wanted.subject.company.service.CompanyService;
import wanted.subject.recruit.dto.RecruitPatchDto;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.recruit.repository.RecruitRepository;
import wanted.subject.user.dto.UserRequestDto;
import wanted.subject.user.entity.User;
import wanted.subject.user.repository.UserRepository;
import wanted.subject.user.service.UserService;
import wanted.subject.userrecruit.dto.UserRecruitRequestDto;
import wanted.subject.userrecruit.entity.UserRecruit;
import wanted.subject.userrecruit.repository.UserRecruitRepository;
import wanted.subject.userrecruit.service.UserRecruitService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class RecruitServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
        recruitRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void 채용공고등록(){
        //given
        Company company = companyService.createCompany(CompanyRequestDto.builder().name("원티드랩").build());
        RecruitRequestDto recruitRequestDto = RecruitRequestDto.builder()
                .companyId(company.getId())
                .country("한국")
                .region("부산")
                .position("백엔드")
                .reward(100000)
                .tech("Spring")
                .content("급구합니다.")
                .build();
        //when
        Recruit recruit = recruitService.createRecruit(recruitRequestDto);
        //then
        Assertions.assertThat(recruit.getCompany()).isSameAs(company);
        Assertions.assertThat(recruit.getCountry()).isEqualTo("한국");
        Assertions.assertThat(recruit.getRegion()).isEqualTo("부산");
        Assertions.assertThat(recruit.getPosition()).isEqualTo("백엔드");
        Assertions.assertThat(recruit.getReward()).isEqualTo(100000);
        Assertions.assertThat(recruit.getTech()).isEqualTo("Spring");
        Assertions.assertThat(recruit.getContent()).isEqualTo("급구합니다.");
    }

    @Test
    public void 채용공고수정 () {
        //given
        Company company = companyService.createCompany(CompanyRequestDto.builder().name("원티드랩").build());
        RecruitRequestDto recruitRequestDto = RecruitRequestDto.builder()
                .companyId(company.getId())
                .country("한국")
                .region("부산")
                .position("백엔드")
                .reward(100000)
                .tech("Spring")
                .content("급구합니다.")
                .build();
        Recruit recruit = recruitService.createRecruit(recruitRequestDto);
        //when
        RecruitPatchDto recruitPatchDto = RecruitPatchDto.builder()
                .country("미국") // 변경
                .region("LA")   // 변경
                .position("백엔드")
                .reward(100000)
                .tech("Spring")
                .content("급구합니다.")
                .build();
        recruitService.updateRecruit(recruit.getId(), recruitPatchDto);
        //then
        Assertions.assertThat(recruit.getCountry()).isEqualTo("미국");
        Assertions.assertThat(recruit.getRegion()).isEqualTo("LA");
        Assertions.assertThat(recruit.getPosition()).isEqualTo("백엔드");

        Assertions.assertThat(recruit.getCountry()).isNotEqualTo("한국");
        Assertions.assertThat(recruit.getRegion()).isNotEqualTo("부산");
    }

    @Test
    public void 채용공고삭제 () {
        // given
        Company company = companyService.createCompany(CompanyRequestDto.builder().name("원티드랩").build());
        RecruitRequestDto recruitRequestDto = RecruitRequestDto.builder()
                .companyId(company.getId())
                .build();
        Recruit recruit = recruitService.createRecruit(recruitRequestDto);
        // when
        recruitService.deleteRecruit(recruit.getId());
        // then
        Assertions.assertThat(recruitRepository.findById(recruit.getId())).isEmpty();
    }

    @Test
    public void 채용공고확인 () {
        // given
        Company company = companyService.createCompany(CompanyRequestDto.builder().name("원티드랩").build());
        RecruitRequestDto recruitRequestDto = RecruitRequestDto.builder().companyId(company.getId()).build();
        Recruit recruit1 = recruitService.createRecruit(recruitRequestDto);
        // when
        Recruit recruit2 = recruitService.verifiedRecruit(recruit1.getId());
        // then
        Assertions.assertThat(recruit1).isSameAs(recruit2);
    }

    @Test
    public void 채용공고확인실패 () {
        // given
        Company company = companyService.createCompany(CompanyRequestDto.builder().name("원티드랩").build());
        RecruitRequestDto recruitRequestDto = RecruitRequestDto.builder().companyId(company.getId()).build();
        Recruit recruit = recruitService.createRecruit(recruitRequestDto);
        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> recruitService.verifiedRecruit(recruit.getId() + 1L));
        String errorMessage = e.getMessage();
        // then
        assertTrue(errorMessage.contains("채용 공고 Id 가 유효하지 않습니다."));
    }


}