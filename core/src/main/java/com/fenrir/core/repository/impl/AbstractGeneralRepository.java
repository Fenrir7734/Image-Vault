package com.fenrir.core.repository.impl;

import com.fenrir.core.repository.GeneralRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

import static com.fenrir.core.common.utils.JooqUtils.sortFields;

@RequiredArgsConstructor
abstract class AbstractGeneralRepository<T extends Table<R>, R extends UpdatableRecordImpl<R>> implements GeneralRepository<T, R> {
    protected final DSLContext dsl;
    protected T table;

    @Override
    public <V> Optional<R> findOne(V value, TableField<R, V> field) {
        return dsl.selectFrom(table)
                .where(field.eq(value))
                .fetchOptional();
    }

    @Override
    public <V> R getOne(V value, TableField<R, V> field) {
        return dsl.selectFrom(table)
                .where(field.eq(value))
                .fetchOne();
    }

    @Override
    public Collection<R> findAll() {
        return dsl.selectFrom(table).fetchInto(table);
    }

    @Override
    public <V> Collection<R> findAll(V value, TableField<R, V> field) {
        return dsl.selectFrom(table)
                .where(field.eq(value))
                .fetchInto(table);
    }

    @Override
    public Page<R> findAll(Pageable pageable) {
        return paginate(DSL.noCondition(), pageable);
    }

    @Override
    public <V> Page<R> findAll(V value, TableField<R, V> field, Pageable pageable) {
        return paginate(field.eq(value), pageable);
    }

    @Override
    public R update(R r) {
        r.update();
        return r;
    }

    @Override
    public R insert(R r) {
        r.insert();
        return r;
    }

    @Override
    public <V> void delete(V value, TableField<R, V> field) {
        dsl.deleteFrom(table)
                .where(field.eq(value))
                .execute();
    }

    protected Page<R> paginate(Condition condition, Pageable pageable) {
        return new PageImpl<>(
                dsl.selectFrom(table)
                        .where(condition)
                        .orderBy(sortFields(table, pageable.getSort()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchInto(table),
                pageable,
                dsl.fetchCount(table, condition)
        );
    }
}
