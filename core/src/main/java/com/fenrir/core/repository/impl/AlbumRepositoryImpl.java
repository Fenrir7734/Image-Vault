package com.fenrir.core.repository.impl;

import com.fenrir.core.database.tables.Albums;
import com.fenrir.core.database.tables.records.AlbumsRecord;
import com.fenrir.core.repository.AlbumRepository;
import org.jooq.DSLContext;

public class AlbumRepositoryImpl extends AbstractGeneralRepository<Albums, AlbumsRecord> implements AlbumRepository {

    public AlbumRepositoryImpl(DSLContext dsl) {
        super(dsl);
    }
}
