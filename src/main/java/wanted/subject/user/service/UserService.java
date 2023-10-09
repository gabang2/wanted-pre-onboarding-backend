package wanted.subject.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.user.entity.User;
import wanted.subject.user.repository.UserRepository;
import wanted.subject.user.dto.UserRequestDto;
import wanted.subject.user.mapper.UserMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    /**
     * CREATE : 생성
     *
     * @param userRequestDto
     */
    public User createUser(UserRequestDto userRequestDto) {

        User user = User.builder().name(userRequestDto.getName()).build();  // user 생성

        return userRepository.save(user);
    }

    /**
     * userId 검증
     * @param userId
     * @return
     */
    public User verifiedUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("userId가 유효하지 않습니다."));
    }

}
