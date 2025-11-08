package dev.oudom.mbanking.features.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserChangePasswordRequest(

        @NotBlank
        String phoneNumber,

        @NotBlank
        String oldPassword,

        @NotBlank
        String newPassword,

        @NotBlank
        String confirmedPassword
) {
}
