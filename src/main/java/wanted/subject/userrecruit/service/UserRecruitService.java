package wanted.subject.userrecruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.recruit.entity.Recruit;
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

        // 외래키로 엔티티 추출
        User user = userService.verifiedUser(userRecruitRequestDto.getUserId());
        Recruit recruit = recruitService.verifiedRecruit(userRecruitRequestDto.getRecruitId());

        // 채용 공고에 지원한 적 있는지 검증, 지원했다면 Exception 발생
        verifiedUserRecruit(user, recruit);

        // 채용 공고 지원
        UserRecruit userRecruit = new UserRecruit();
        userRecruit.setUser(user);
        userRecruit.setRecruit(recruit);

        return userRecruitRepository.save(userRecruit);
    }

    /**
     * 유저가 채용공고 최초로 지원하는지 검증
     * @param user
     * @param recruit
     */
    private static void verifiedUserRecruit(User user, Recruit recruit) {
        for (UserRecruit userRecruit : user.getUserRecruits()) {
            if (recruit == userRecruit.getRecruit()) {
                throw new RuntimeException("한 번 지원한 공고는 두 번 지원할 수 없습니다.");
            }
        }
    }
}
