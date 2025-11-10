package dev.oudom.mbanking.mapper;

import dev.oudom.mbanking.domain.User;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import dev.oudom.mbanking.features.user.dto.UserResponse;
import dev.oudom.mbanking.features.user.dto.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // SourceType = UserCreateRequest (Parameter)
    // TargetType = User (ReturnType)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);
}
