package com.fenrir.core.feature.user.dto;

import com.fenrir.core.domain.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class MeResponse {
    private final Long id;

    private final String email;
    private final String externalName;
    private final String name;
    private final Boolean verified;
    private final Boolean enabled;

    private final Long roleId;
    private final Role role;

    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;
}
