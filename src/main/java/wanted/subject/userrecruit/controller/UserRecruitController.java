package wanted.subject.userrecruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.subject.user.entity.User;
import wanted.subject.userrecruit.dto.UserRecruitRequestDto;
import wanted.subject.userrecruit.dto.UserRecruitResponseDto;
import wanted.subject.userrecruit.entity.UserRecruit;
import wanted.subject.userrecruit.mapper.UserRecruitMapper;
import wanted.subject.userrecruit.service.UserRecruitService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-recruits")
public class UserRecruitController {
    private final UserRecruitService userRecruitService;
    private final UserRecruitMapper userRecruitMapper;

    /**
     * UserRecruit 생성
     * @param userRecruitRequestDto
     * @return ResponseEntity(201)
     */
    @PostMapping
    public ResponseEntity createUserRecruit(@Valid @RequestBody UserRecruitRequestDto userRecruitRequestDto) {
        UserRecruit userRecruit = userRecruitService.createUserRecruit(userRecruitRequestDto);
        UserRecruitResponseDto response = userRecruitMapper.userRecruitToUserRecruitResponseDTO(userRecruit);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
