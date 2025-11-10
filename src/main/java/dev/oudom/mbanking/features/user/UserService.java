package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.base.BasedMessage;
import dev.oudom.mbanking.features.user.dto.UserChangePasswordRequest;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import dev.oudom.mbanking.features.user.dto.UserResponse;
import dev.oudom.mbanking.features.user.dto.UserUpdateRequest;

public interface UserService {

    void createNew(UserCreateRequest userCreateRequest);

    void changePassword(UserChangePasswordRequest userChangePasswordRequest);

    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    UserResponse findByUuid(String uuid);

    BasedMessage blockByUuid(String uuid);

    void deleteByUuid(String uuid);

    BasedMessage disableByUuid(String uuid);

    BasedMessage enableByUuid(String uuid);
}
