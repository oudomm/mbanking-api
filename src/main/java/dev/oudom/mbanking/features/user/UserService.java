package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.features.user.dto.UserChangePasswordRequest;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import dev.oudom.mbanking.features.user.dto.UserUpdateProfileRequest;

public interface UserService {

    void createNew(UserCreateRequest userCreateRequest);

    void changePassword(UserChangePasswordRequest userChangePasswordRequest);

    void updateProfileByUuid(UserUpdateProfileRequest userUpdateProfileRequest, String uuid);
}
