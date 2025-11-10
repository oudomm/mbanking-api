package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.base.BasedMessage;
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

    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(
            @PathVariable String uuid,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        return userService.updateByUuid(uuid, request);
    }

    @GetMapping("/{uuid}")
    UserResponse getByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteByUuid(@PathVariable String uuid) {
        userService.deleteByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    BasedMessage disableByUuid(@PathVariable String uuid) {
        return userService.disableByUuid(uuid);
    }

    @PutMapping("/{uuid}/enable")
    BasedMessage enableByUuid(@PathVariable String uuid) {
        return userService.enableByUuid(uuid);
    }
}
