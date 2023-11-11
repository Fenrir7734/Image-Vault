package com.fenrir.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExchangeRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
