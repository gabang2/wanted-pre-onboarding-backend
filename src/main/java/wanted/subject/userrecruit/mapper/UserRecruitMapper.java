package wanted.subject.userrecruit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.subject.userrecruit.dto.UserRecruitRequestDto;
import wanted.subject.userrecruit.dto.UserRecruitResponseDto;
import wanted.subject.userrecruit.entity.UserRecruit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRecruitMapper {

    // UserRecruit -> UserRecruitResponseDTO
    UserRecruitResponseDto userRecruitToUserRecruitResponseDTO(UserRecruit userRecruit);

    // UserRecruitRequestDTO -> UserRecruit
    UserRecruit userRecruitRequestDtoToUserRecruit(UserRecruitRequestDto UserRecruitRecruitRequestDto);
}
