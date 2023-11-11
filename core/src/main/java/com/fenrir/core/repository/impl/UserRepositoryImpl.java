package com.fenrir.core.repository.impl;

import com.fenrir.core.database.Tables;
import com.fenrir.core.database.tables.Users;
import com.fenrir.core.database.tables.records.UsersRecord;
import com.fenrir.core.domain.UserEntity;
import com.fenrir.core.repository.UserRepository;
import org.jooq.DSLContext;
import org.jooq.SelectFieldOrAsterisk;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.fenrir.core.common.utils.JooqEnumConverter.authTypeConverter;
import static com.fenrir.core.common.utils.JooqEnumConverter.roleConverter;

@Repository
public class UserRepositoryImpl extends AbstractGeneralRepository<Users, UsersRecord> implements UserRepository {

    public UserRepositoryImpl(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        final com.fenrir.core.database.tables.Users users = Tables.USERS.as("u");
        final com.fenrir.core.database.tables.Roles roles = Tables.ROLES.as("r");

        return dsl.select(userFields(users, roles))
                .from(users)
                .innerJoin(roles).on(roles.ID.eq(users.ROLE_ID))
                .where(users.ID.eq(id))
                .fetchOptionalInto(UserEntity.class);
    }

    private Collection<? extends SelectFieldOrAsterisk> userFields(
            com.fenrir.core.database.tables.Users users,
            com.fenrir.core.database.tables.Roles roles
    ) {
        return List.of(
                users.ID,
                users.CODE,
                users.AUTH_TYPE.convert(authTypeConverter()),
                users.EXTERNAL_ID,
                users.EXTERNAL_NAME,
                users.NAME,
                users.EMAIL,
                users.VERIFIED,
                users.ENABLED,
                roles.ID.as(UserEntity.Fields.roleId),
                roles.NAME.convert(roleConverter()).as(UserEntity.Fields.role),
                users.UPDATED_AT,
                users.CREATED_AT
        );
    }
}
