package dev.oudom.mbanking.mapper;

import dev.oudom.mbanking.domain.User;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // SourceType = UserCreateRequest (Parameter)
    // TargetType = User (ReturnType)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

}
