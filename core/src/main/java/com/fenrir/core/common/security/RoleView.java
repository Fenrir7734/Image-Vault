package com.fenrir.core.common.security;

import com.fenrir.core.domain.enums.Role;

import java.util.HashMap;
import java.util.Map;

public class RoleView {
    public static class Viewer {}
    public static class User extends Viewer {}
    public static class Admin extends User {}

    public static final Map<Role, Class> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Role.VIEWER, Viewer.class);
        MAPPING.put(Role.USER, User.class);
        MAPPING.put(Role.ADMIN, Admin.class);
    }
}
