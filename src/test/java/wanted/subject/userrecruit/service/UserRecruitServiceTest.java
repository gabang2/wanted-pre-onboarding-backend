package wanted.subject.userrecruit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.company.dto.CompanyRequestDto;
import wanted.subject.company.entity.Company;
import wanted.subject.company.repository.CompanyRepository;
import wanted.subject.company.service.CompanyService;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.recruit.repository.RecruitRepository;
import wanted.subject.recruit.service.RecruitService;
import wanted.subject.user.dto.UserRequestDto;
import wanted.subject.user.entity.User;
import wanted.subject.user.repository.UserRepository;
import wanted.subject.user.service.UserService;
import wanted.subject.userrecruit.dto.UserRecruitRequestDto;
import wanted.subject.userrecruit.entity.UserRecruit;
import wanted.subject.userrecruit.repository.UserRecruitRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRecruitServiceTest {

    @Autowired
    private UserRecruitService userRecruitService;

    @Autowired
    private UserRecruitRepository userRecruitRepository;

    @Autowired
    private UserService userService;

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
    void clean(){
        userRecruitRepository.deleteAll();
        userRepository.deleteAll();
        recruitRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void 지원내역생성 () {
        // given
        User user = userService.createUser(UserRequestDto.builder().name("김가영").build());
        Company company = companyService.createCompany(CompanyRequestDto.builder().name("원티드랩").build());
        Recruit recruit = recruitService.createRecruit(RecruitRequestDto.builder().companyId(company.getId()).position("백엔드").build());
        UserRecruitRequestDto userRecruitRequestDto = UserRecruitRequestDto.builder().userId(user.getId()).recruitId(recruit.getId()).build();
        // when
        UserRecruit userRecruit = userRecruitService.createUserRecruit(userRecruitRequestDto);
        // then
        Assertions.assertThat(userRecruit.getUser().getName()).isEqualTo("김가영");
        Assertions.assertThat(userRecruit.getRecruit().getPosition()).isEqualTo("백엔드");
        Assertions.assertThat(userRecruit.getRecruit().getCompany().getName()).isEqualTo("원티드랩");
    }

    @Test
    public void 여러번지원하면에러발생 () {
        // given
        User user = userService.createUser(UserRequestDto.builder().name("김가영").build());
        Company company = companyService.createCompany(CompanyRequestDto.builder().name("원티드랩").build());
        Recruit recruit = recruitService.createRecruit(RecruitRequestDto.builder().companyId(company.getId()).position("백엔드").build());
        UserRecruitRequestDto userRecruitRequestDto = UserRecruitRequestDto.builder().userId(user.getId()).recruitId(recruit.getId()).build();
        UserRecruit userRecruit = userRecruitService.createUserRecruit(userRecruitRequestDto);
        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> userRecruitService.createUserRecruit(userRecruitRequestDto));
        String errorMessage = e.getMessage();
        // then
        assertTrue(errorMessage.contains("한 번 지원한 공고는 두 번 지원할 수 없습니다."));
    }
}