package wanted.subject.user.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import wanted.subject.company.dto.CompanyRequestDto;
import wanted.subject.company.entity.Company;
import wanted.subject.user.dto.UserRequestDto;
import wanted.subject.user.entity.User;
import wanted.subject.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clean(){
        userRepository.deleteAll();
    }

    @Test
    public void 사용자생성 () {
        // given
        UserRequestDto userRequestDto = UserRequestDto.builder().name("김가영").build();
        // when
        User user = userService.createUser(userRequestDto);
        // then
        Assertions.assertThat(user.getName()).isEqualTo("김가영");
    }

    @Test
    public void 사용자확인 () {
        // given
        UserRequestDto userRequestDto = UserRequestDto.builder().name("김가영'").build();
        User user1= userService.createUser(userRequestDto);
        // when
        User user2 = userService.verifiedUser(user1.getId());
        // then
        Assertions.assertThat(user1).isSameAs(user2);
    }

    @Test
    public void 사용자확인실패 () {
        // given
        UserRequestDto userRequestDto = UserRequestDto.builder().name("김가영").build();
        User user= userService.createUser(userRequestDto);
        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> userService.verifiedUser(user.getId() + 1L));
        String errorMessage = e.getMessage();
        // then
        assertTrue(errorMessage.contains("userId가 유효하지 않습니다."));
    }

}