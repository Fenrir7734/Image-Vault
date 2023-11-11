package com.fenrir.auth.dto.request;

import com.fenrir.auth.validator.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRequest {
    @NotBlank
    @Size(min = 2, max = 100)
    private String username;

    @NotBlank
    @Size(max = 256)
    @Email
    private String email;

    @Password
    private String password;

    @NotBlank
    private String passwordRepeat;
}
