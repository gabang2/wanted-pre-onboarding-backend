package wanted.subject.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.service.RecruitService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruits")
public class RecruitController {
    private final RecruitService recruitService;
    @PostMapping
    public ResponseEntity createRecruit(@Valid @RequestBody RecruitRequestDto recruitRequestDto) {
        recruitService.createRecruit(recruitRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
