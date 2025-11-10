package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.features.user.dto.UserChangePasswordRequest;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import dev.oudom.mbanking.features.user.dto.UserResponse;
import dev.oudom.mbanking.features.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        userService.createNew(userCreateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/change-password")
    void changePassword(@Valid @RequestBody UserChangePasswordRequest request) {
        userService.changePassword(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/update-profile/{uuid}")
    UserResponse updateByUuid(
            @PathVariable String uuid,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        return userService.updateByUuid(uuid, request);
    }
}
