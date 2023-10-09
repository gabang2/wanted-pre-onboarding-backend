package wanted.subject.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.subject.user.dto.UserRequestDto;
import wanted.subject.user.dto.UserResponseDto;
import wanted.subject.user.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    // User -> UserResponseDTO
    UserResponseDto userToUserResponseDTO(User user);

    // UserRequestDTO -> User
    User userRequestDtoToUser(UserRequestDto UserRequestDto);
}
