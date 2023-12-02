package com.fenrir.core.repository.impl;

import com.fenrir.core.database.Tables;
import com.fenrir.core.database.tables.Albums;
import com.fenrir.core.database.tables.records.AlbumsRecord;
import com.fenrir.core.domain.AlbumEntity;
import com.fenrir.core.repository.AlbumRepository;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectFieldOrAsterisk;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static com.fenrir.core.common.utils.JooqEnumConverter.albumVisibilityConverter;
import static com.fenrir.core.common.utils.JooqUtils.sortFields;
import static com.fenrir.core.domain.enums.AlbumVisibility.PUBLIC_RO;
import static com.fenrir.core.domain.enums.AlbumVisibility.PUBLIC_RW;

@Repository
public class AlbumRepositoryImpl extends AbstractGeneralRepository<Albums, AlbumsRecord> implements AlbumRepository {

    public AlbumRepositoryImpl(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public Page<AlbumEntity> findAll(Long userId, Pageable pageable) {
        final com.fenrir.core.database.tables.Albums albums = Tables.ALBUMS.as("a");
        final com.fenrir.core.database.tables.AlbumVisibility visibilities = Tables.ALBUM_VISIBILITY.as("av");
        final com.fenrir.core.database.tables.Users users = Tables.USERS.as("u");

        final Condition condition = DSL.or(
                albums.OWNER_ID.eq(userId),
                visibilities.NAME.in(PUBLIC_RO.name(), PUBLIC_RW.name())
        );

        final var select = dsl.select(albumFields(albums, visibilities, users))
                .from(albums)
                .innerJoin(visibilities).on(visibilities.ID.eq(albums.VISIBILITY_ID))
                .innerJoin(users).on(users.ID.eq(albums.OWNER_ID))
                .where(condition);

        return new PageImpl<>(
                select.orderBy(sortFields(albums, pageable.getSort(), albums.NAME.asc()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchInto(AlbumEntity.class),
                pageable,
                dsl.fetchCount(select)
        );
    }

    private Collection<? extends SelectFieldOrAsterisk> albumFields(
            com.fenrir.core.database.tables.Albums albums,
            com.fenrir.core.database.tables.AlbumVisibility visibilities,
            com.fenrir.core.database.tables.Users users
    ) {
        return List.of(
                albums.ID,
                albums.NAME,
                albums.DESCRIPTION,
                users.ID,
                users.NAME,
                visibilities.NAME.convert(albumVisibilityConverter()).as(AlbumEntity.Fields.visibility),
                albums.UPDATED_AT,
                albums.CREATED_AT
        );
    }
}
