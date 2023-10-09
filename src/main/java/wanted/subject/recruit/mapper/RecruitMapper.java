package wanted.subject.recruit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.dto.RecruitResponseDto;
import wanted.subject.recruit.entity.Recruit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecruitMapper {

    // User -> RecruitResponseDTO
    RecruitResponseDto recruitToRecruitResponseDTO(Recruit recruit);

    // RecruitRequestDTO -> User
    Recruit recruitRequestDtoToRecruit(RecruitRequestDto RecruitRequestDto);
}
