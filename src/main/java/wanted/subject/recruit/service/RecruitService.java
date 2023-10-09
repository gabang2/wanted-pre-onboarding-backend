package wanted.subject.recruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.company.service.CompanyService;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.entity.Recruit;
import wanted.subject.recruit.mapper.RecruitMapper;
import wanted.subject.recruit.repository.RecruitRepository;

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
}
