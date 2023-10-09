package wanted.subject.recruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.company.service.CompanyService;
import wanted.subject.recruit.dto.RecruitPatchDto;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.recruit.mapper.RecruitMapper;
import wanted.subject.recruit.repository.RecruitRepository;

import java.util.ArrayList;
import java.util.List;

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


    public List<Recruit> getRecruitList(String searchKeyword) {
        if (searchKeyword != null) return recruitRepository.findAll();
        return recruitRepository.findAll();
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
