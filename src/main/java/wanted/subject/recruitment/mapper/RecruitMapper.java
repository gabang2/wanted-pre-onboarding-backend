package wanted.subject.recruitment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.subject.recruitment.dto.RecruitRequestDto;
import wanted.subject.recruitment.dto.RecruitResponseDto;
import wanted.subject.recruitment.entity.Recruit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecruitMapper {

    // User -> RecruitResponseDTO
    RecruitResponseDto recruitToRecruitResponseDTO(Recruit recruit);

    // RecruitRequestDTO -> User
    Recruit recruitRequestDtoToRecruit(RecruitRequestDto RecruitRequestDto);
}
