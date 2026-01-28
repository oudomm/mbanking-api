package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.base.BasedMessage;
import dev.oudom.mbanking.domain.Role;
import dev.oudom.mbanking.domain.User;
import dev.oudom.mbanking.features.user.dto.UserChangePasswordRequest;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import dev.oudom.mbanking.features.user.dto.UserResponse;
import dev.oudom.mbanking.features.user.dto.UserUpdateRequest;
import dev.oudom.mbanking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public void createNew(UserCreateRequest userCreateRequest) {

        if (userRepository.existsByNationalCardId(userCreateRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID has already been existed!"
            );
        }

        if (userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }

        if (userRepository.existsByStudentIdCard(userCreateRequest.studentIdCard())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already been existed!"
            );
        }

        if (!userCreateRequest.password().equals(userCreateRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        // DTO pattern (mapstruct ft. lombok)
        User user = userMapper.fromUserCreateRequest(userCreateRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setProfileImage("avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setIsDeleted(false);

        // Assign default user role
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role USER has not been found!"));

        // Create dynamic role from client
        userCreateRequest.roles().forEach(r -> {
            Role newRole = roleRepository.findByName(r.name())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Role " + r.name() + " does not exists!"));
            roles.add(newRole);
        });

        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAllByIsDeletedFalseAndIsBlockedFalse();
        return users.stream().map(user -> userMapper.toUserResponse(user)).toList();
    }

    @Override
    public void changePassword(UserChangePasswordRequest userChangePasswordRequest) {

        if (!userChangePasswordRequest.newPassword().equals(userChangePasswordRequest.confirmedPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password and confirmed password do not match");
        }

        User user = userRepository.findByPhoneNumber(userChangePasswordRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));

        if (!user.getPassword().equals(userChangePasswordRequest.oldPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password is incorrect");
        }

        user.setPassword(userChangePasswordRequest.newPassword());

        userRepository.save(user);
    }

    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));

        log.info("before user: {}", user.getName());
        log.info("before user: {}", user.getPosition());
        log.info("before user: {}", user.getStudentIdCard());

        userMapper.fromUserUpdateRequest(userUpdateRequest, user);

        log.info("after user: {}", user.getName());
        log.info("after user: {}", user.getPosition());
        log.info("after user: {}", user.getStudentIdCard());

        user = userRepository.save(user);

        return userMapper.toUserResponse(user);

    }

    @Override
    public UserResponse findByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));

        return userMapper.toUserResponse(user);
    }

    @Override
    public BasedMessage blockByUuid(String uuid) {

        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        userRepository.blockByUuid(uuid);

        return new BasedMessage("User has been blocked successfully");
    }

    @Override
    public void deleteByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));

        userRepository.delete(user);
    }

    @Override
    public BasedMessage disableByUuid(String uuid) {

        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        userRepository.disableByUuid(uuid);

        return new BasedMessage("User has been disabled successfully");
    }

    @Override
    public BasedMessage enableByUuid(String uuid) {

        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        userRepository.enableByUuid(uuid);

        return new BasedMessage("User has been enabled successfully");
    }
}
