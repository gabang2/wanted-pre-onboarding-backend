package wanted.subject.recruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.company.service.CompanyService;
import wanted.subject.recruit.dto.RecruitDetailResponseDto;
import wanted.subject.recruit.dto.RecruitPatchDto;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.dto.RecruitResponseDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.recruit.mapper.RecruitMapper;
import wanted.subject.recruit.repository.RecruitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitService {
    private final RecruitMapper recruitMapper;
    private final RecruitRepository recruitRepository;
    private final CompanyService companyService;

    /**
     * CREATE : 생성
     *
     * @param recruitRequestDto
     */
    public Recruit createRecruit(RecruitRequestDto recruitRequestDto) {
        Recruit recruit = recruitMapper.recruitRequestDtoToRecruit(recruitRequestDto);
        recruit.setCompany(companyService.verifiedCompany(recruitRequestDto.getCompanyId()));
        return recruitRepository.save(recruit);
    }

    /**
     * Update : 수정
     *
     * @param recruitId
     * @param recruitPatchDto
     * @return Recruit
     */
    public Recruit updateRecruit(Long recruitId, RecruitPatchDto recruitPatchDto) {
        Recruit recruit = verifiedRecruit(recruitId);
        recruit.updateRecruit(
                recruitPatchDto.getCountry(),
                recruitPatchDto.getRegion(),
                recruitPatchDto.getPosition(),
                recruitPatchDto.getReward(),
                recruitPatchDto.getTech(),
                recruitPatchDto.getContent()
        );
        return recruit;
    }

    /**
     * DELETE : 삭제
     *
     * @param recruitId
     */
    public void deleteRecruit(Long recruitId) {
        recruitRepository.delete(verifiedRecruit(recruitId));
    }


    /**
     * GET : 채용 공고 목록 조회
     *
     * @param searchKeyword
     * @return
     */
    @Transactional(readOnly = true)
    public List<RecruitResponseDto> getRecruitResponseDtoList(String searchKeyword) {

        List<Recruit> recruitList = recruitRepository.findAll();

        List<RecruitResponseDto> RecruitResponseDtoList = new ArrayList<>();
        for (Recruit recruit : recruitList) {
            RecruitResponseDto recruitResponseDto = recruitMapper.recruitToRecruitResponseDTO(recruit);
            recruitResponseDto.setCompanyName(recruit.getCompany().getName());
            RecruitResponseDtoList.add(recruitResponseDto);
        }

        return RecruitResponseDtoList;
    }

    /**
     * 채용 공고 상세 조회
     *
     * @param recruitId
     * @return RecruitDetailResponseDto
     */
    @Transactional(readOnly = true)
    public RecruitDetailResponseDto getRecruitDetailResponseDto(Long recruitId) {

        // recruitId -> Recruit
        Recruit recruit = verifiedRecruit(recruitId);

        // 채용 공고 상세 dto (회사 이름, 현재 공고를 제외한 회사의 다른 공고도 조회하도록 설정)
        RecruitDetailResponseDto recruitDetailResponseDto = recruitMapper.recruitToRecruitDetailResponseDTO(recruit);   // Recruit -> RecruitDetailResponseDto
        recruitDetailResponseDto.setCompanyName(recruit.getCompany().getName());                                        // 회사 이름 설정
        recruitDetailResponseDto.setAnotherRecruit(                                                                     // 회시가 올린 다른 채용 공고 id 조회
                recruit.getCompany().getRecruits().stream()
                        .filter(r -> !r.getId().equals(recruitId))
                        .map(Recruit::getId)
                        .collect(Collectors.toList())
        );
        return recruitDetailResponseDto;
    }

    /**
     * recruitId가 유효한지 검증
     *
     * @param recruitId
     * @return
     */
    public Recruit verifiedRecruit(Long recruitId) {
        return recruitRepository.findById(recruitId).orElseThrow(() -> new RuntimeException("채용 공고 Id 가 유효하지 않습니다."));
    }
}
