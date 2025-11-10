package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.features.user.dto.UserChangePasswordRequest;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import dev.oudom.mbanking.features.user.dto.UserResponse;
import dev.oudom.mbanking.features.user.dto.UserUpdateRequest;

public interface UserService {

    void createNew(UserCreateRequest userCreateRequest);

    void changePassword(UserChangePasswordRequest userChangePasswordRequest);

    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);
}
