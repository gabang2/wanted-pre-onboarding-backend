package wanted.subject.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.subject.recruit.dto.RecruitPatchDto;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.dto.RecruitResponseDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.recruit.mapper.RecruitMapper;
import wanted.subject.recruit.service.RecruitService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruits")
public class RecruitController {
    private final RecruitService recruitService;
    private final RecruitMapper recruitMapper;

    /**
     * 채용 공고 생성
     * @param recruitRequestDto
     * @return ResponseEntity(201)
     */
    @PostMapping
    public ResponseEntity createRecruit(@Valid @RequestBody RecruitRequestDto recruitRequestDto) {
        Recruit recruit = recruitService.createRecruit(recruitRequestDto);
        RecruitResponseDto response = recruitMapper.recruitToRecruitResponseDTO(recruit);
        response.setCompanyName(recruit.getCompany().getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * 채용공고 수정
     * @param recruitId
     * @param recruitPatchDto
     * @return ResponseEntity(200)
     */
    @PatchMapping("/{recruit-id}")
    public ResponseEntity patchRecruit(@Positive @PathVariable("recruit-id") Long recruitId,
                                       @Valid @RequestBody RecruitPatchDto recruitPatchDto) {
        Recruit recruit = recruitService.updateRecruit(recruitId, recruitPatchDto);
        RecruitResponseDto response = recruitMapper.recruitToRecruitResponseDTO(recruit);
        response.setCompanyName(recruit.getCompany().getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 채용 공고 삭제(DB 삭제)
     * @param recruitId
     * @return ResponseEntity(204)
     */
    @DeleteMapping("/{recruit-id}")
    public ResponseEntity deleteRecruit(@Positive @PathVariable("recruit-id") Long recruitId) {
        recruitService.deleteRecruit(recruitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
