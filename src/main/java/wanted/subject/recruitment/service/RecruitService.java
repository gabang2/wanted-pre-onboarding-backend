package wanted.subject.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.subject.recruitment.dto.RecruitRequestDto;
import wanted.subject.recruitment.entity.Recruit;
import wanted.subject.recruitment.mapper.RecruitMapper;
import wanted.subject.recruitment.repository.RecruitRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitService {
    private final RecruitMapper recruitMapper;
    private final RecruitRepository recruitRepository;

    /**
     * CREATE : 생성
     *
     * @param recruitRequestDto
     */
    public Recruit createRecruit(RecruitRequestDto recruitRequestDto) {
        return recruitRepository.save(recruitMapper.recruitRequestDtoToRecruit(recruitRequestDto));
    }

}
