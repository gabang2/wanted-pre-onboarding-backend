package wanted.subject.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.subject.user.dto.UserResponseDto;
import wanted.subject.user.entity.User;
import wanted.subject.user.service.UserService;
import wanted.subject.user.dto.UserRequestDto;
import wanted.subject.user.mapper.UserMapper;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * User 생성
     * @param userRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = userService.createUser(userRequestDto);
        UserResponseDto response = userMapper.userToUserResponseDTO(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
