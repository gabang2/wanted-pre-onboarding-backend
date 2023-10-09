package wanted.subject.userrecruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.recruit.service.RecruitService;
import wanted.subject.user.entity.User;
import wanted.subject.user.service.UserService;
import wanted.subject.userrecruit.dto.UserRecruitRequestDto;
import wanted.subject.userrecruit.entity.UserRecruit;
import wanted.subject.userrecruit.mapper.UserRecruitMapper;
import wanted.subject.userrecruit.repository.UserRecruitRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRecruitService {
    private final UserRecruitRepository userRecruitRepository;
    private final UserService userService;
    private final RecruitService recruitService;

    /**
     * CREATE : 생성
     *
     * @param userRecruitRequestDto
     */
    public UserRecruit createUserRecruit(UserRecruitRequestDto userRecruitRequestDto) {

        UserRecruit userRecruit = new UserRecruit();
        userRecruit.setUser(userService.verifiedUser(userRecruitRequestDto.getUserId()));
        userRecruit.setRecruit(recruitService.verifiedRecruit(userRecruitRequestDto.getRecruitId()));

        return userRecruitRepository.save(userRecruit);
    }

}
