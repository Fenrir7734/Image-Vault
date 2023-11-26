package com.fenrir.auth.repository;

import com.fenrir.auth.entity.StartupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<StartupEntity, String> {
}
