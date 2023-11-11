package com.fenrir.core.repository;

import com.fenrir.core.database.tables.Users;
import com.fenrir.core.database.tables.records.UsersRecord;
import com.fenrir.core.domain.UserEntity;

import java.util.Optional;

public interface UserRepository extends GeneralRepository<Users, UsersRecord> {

    Optional<UserEntity> findOne(Long id);
}
