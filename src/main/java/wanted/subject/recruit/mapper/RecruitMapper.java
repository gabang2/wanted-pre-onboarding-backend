package wanted.subject.recruit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.subject.recruit.dto.RecruitRequestDto;
import wanted.subject.recruit.dto.RecruitResponseDto;
import wanted.subject.recruit.entity.Recruit;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecruitMapper {

    // User -> RecruitResponseDTO
    RecruitResponseDto recruitToRecruitResponseDTO(Recruit recruit);

    // RecruitRequestDTO -> User
    Recruit recruitRequestDtoToRecruit(RecruitRequestDto RecruitRequestDto);

    List<RecruitResponseDto> recruitListToRecruitResponseDTOList(List<Recruit> recruitList);
}
