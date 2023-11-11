package com.fenrir.core.security.user;

import com.fenrir.core.domain.enums.Role;

record RegisteredUser(
        Long id,
        String email,
        Boolean verified,
        Boolean enabled,
        Role role) { }
