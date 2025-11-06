package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.features.user.dto.UserCreateRequest;

public interface UserService {

    void createNew(UserCreateRequest userCreateRequest);
}
