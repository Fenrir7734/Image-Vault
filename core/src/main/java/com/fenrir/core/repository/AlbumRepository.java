package com.fenrir.core.repository;

import com.fenrir.core.database.tables.Albums;
import com.fenrir.core.database.tables.records.AlbumsRecord;
import com.fenrir.core.domain.AlbumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumRepository extends GeneralRepository<Albums, AlbumsRecord> {
    Page<AlbumEntity> findAll(Long userId, Pageable pageable);
}
