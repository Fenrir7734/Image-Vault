package com.fenrir.core.security.user;

import com.fenrir.core.database.Tables;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SelectFieldOrAsterisk;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.fenrir.core.common.utils.JooqEnumConverter.roleConverter;

@Repository
@RequiredArgsConstructor
class UserPrincipalRepository {
    private final DSLContext dsl;

    public Optional<RegisteredUser> findUser(String email) {
        final com.fenrir.core.database.tables.Users users = Tables.USERS.as("u");
        final com.fenrir.core.database.tables.Roles roles = Tables.ROLES.as("r");

        return dsl.select(registeredUserFields(users, roles))
                .from(users)
                .innerJoin(roles).on(roles.ID.eq(users.ROLE_ID))
                .where(users.EMAIL.eq(email))
                .fetchOptionalInto(RegisteredUser.class);
    }

    private Collection<? extends SelectFieldOrAsterisk> registeredUserFields(
            com.fenrir.core.database.tables.Users users,
            com.fenrir.core.database.tables.Roles roles
    ) {
        return List.of(
                users.ID,
                users.EMAIL,
                users.VERIFIED,
                users.ENABLED,
                roles.NAME.convert(roleConverter())
        );
    }
}
