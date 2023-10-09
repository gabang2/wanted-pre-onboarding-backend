package wanted.subject.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.dto.RecruitResponseDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.recruit.mapper.RecruitMapper;
import wanted.subject.recruit.service.RecruitService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruits")
public class RecruitController {
    private final RecruitService recruitService;
    private final RecruitMapper recruitMapper;
    @PostMapping
    public ResponseEntity createRecruit(@Valid @RequestBody RecruitRequestDto recruitRequestDto) {
        Recruit recruit = recruitService.createRecruit(recruitRequestDto);
        RecruitResponseDto response = recruitMapper.recruitToRecruitResponseDTO(recruit);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
