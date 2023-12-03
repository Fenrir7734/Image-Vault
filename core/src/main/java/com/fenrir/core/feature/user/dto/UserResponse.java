package com.fenrir.core.feature.user.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fenrir.core.common.security.RoleView;
import com.fenrir.core.domain.enums.AuthType;
import com.fenrir.core.domain.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class UserResponse {
    private final Long id;

    @JsonView(RoleView.Admin.class)
    private final String code;
    @JsonView(RoleView.Admin.class)
    private final String email;
    @JsonView(RoleView.Admin.class)
    private final AuthType authType;
    private final String externalName;
    private final String name;
    private final Boolean verified;
    private final Boolean enabled;

    private final Long roleId;
    private final Role role;

    private final LocalDateTime updatedAt;
    private final LocalDateTime createdAt;
}
