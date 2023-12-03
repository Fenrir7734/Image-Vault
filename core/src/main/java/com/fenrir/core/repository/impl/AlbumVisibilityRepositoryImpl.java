package com.fenrir.core.repository.impl;

import com.fenrir.core.database.tables.AlbumVisibility;
import com.fenrir.core.database.tables.records.AlbumVisibilityRecord;
import com.fenrir.core.repository.AlbumVisibilityRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumVisibilityRepositoryImpl extends AbstractGeneralRepository<AlbumVisibility, AlbumVisibilityRecord> implements AlbumVisibilityRepository {
    public AlbumVisibilityRepositoryImpl(DSLContext dsl) {
        super(dsl);
    }
}
