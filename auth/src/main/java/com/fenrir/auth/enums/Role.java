package com.fenrir.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    VIEWER("ROLE_VIEWER");

    private final String name;

    public static List<Role> from(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::from)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public static Optional<Role> from(String roleName) {
        final Role[] roles = Role.values();
        return Arrays.stream(roles)
                .filter(role -> role.name.equals(roleName))
                .findFirst();
    }
}
