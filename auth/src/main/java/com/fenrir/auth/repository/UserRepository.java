package com.fenrir.auth.repository;

import com.fenrir.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("select case when count(u) > 0 then true else false end from UserEntity u")
    boolean existsAnyUser();
}
