package com.fenrir.auth.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JwtClaim {
    USER_ID_CLAIM("userId"),
    ROLES_CLAIM("roles"),
    EMAIL_CLAIM("email"),
    VERIFIED_CLAIM("verified"),
    ENABLED_CLAIM("enabled");

    private final String name;
}
