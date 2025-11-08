package dev.oudom.mbanking.features.user;

import dev.oudom.mbanking.domain.Role;
import dev.oudom.mbanking.domain.User;
import dev.oudom.mbanking.features.user.dto.UserChangePasswordRequest;
import dev.oudom.mbanking.features.user.dto.UserCreateRequest;
import dev.oudom.mbanking.features.user.dto.UserUpdateProfileRequest;
import dev.oudom.mbanking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
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
    public void updateProfileByUuid(UserUpdateProfileRequest request, String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));

        if (request.profileImage() != null) {
            user.setProfileImage(request.profileImage());
        }
        if (request.cityOrProvince() != null) {
            user.setCityOrProvince(request.cityOrProvince());
        }
        if (request.khanOrDistrict() != null) {
            user.setKhanOrDistrict(request.khanOrDistrict());
        }
        if (request.sangkatOrCommune() != null) {
            user.setSangkatOrCommune(request.sangkatOrCommune());
        }
        if (request.village() != null) {
            user.setVillage(request.village());
        }
        if (request.street() != null) {
            user.setStreet(request.street());
        }
        if (request.employeeType() != null) {
            user.setEmployeeType(request.employeeType());
        }
        if (request.position() != null) {
            user.setPosition(request.position());
        }
        if (request.companyName() != null) {
            user.setCompanyName(request.companyName());
        }
        if (request.mainSourceOfIncome() != null) {
            user.setMainSourceOfIncome(request.mainSourceOfIncome());
        }
        if (request.monthlyIncomeRange() != null) {
            user.setMonthlyIncomeRange(request.monthlyIncomeRange());
        }

        userRepository.save(user);
    }
}
