package wanted.subject.recruit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.subject.recruit.dto.RecruitDetailResponseDto;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.dto.RecruitResponseDto;
import wanted.subject.recruit.entity.Recruit;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecruitMapper {

    // Recruit -> RecruitResponseDTO
    RecruitResponseDto recruitToRecruitResponseDTO(Recruit recruit);

    // Recruit -> RecruitDetailResponseDTO
    RecruitDetailResponseDto recruitToRecruitDetailResponseDTO(Recruit recruit);

    // RecruitRequestDTO -> Recruit
    Recruit recruitRequestDtoToRecruit(RecruitRequestDto RecruitRequestDto);
}
