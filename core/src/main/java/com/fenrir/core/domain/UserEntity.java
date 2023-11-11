package com.fenrir.core.domain;

import com.fenrir.core.domain.enums.AuthType;
import com.fenrir.core.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class UserEntity {
    private Long id;
    private String code;
    private AuthType authType;
    private String externalId;
    private String externalName;
    private String name;
    private String email;
    private Boolean verified;
    private Boolean enabled;
    private Long roleId;
    private Role role;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
