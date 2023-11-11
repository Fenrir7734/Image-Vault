package com.fenrir.core.repository;

import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface GeneralRepository<T extends Table<R>, R extends UpdatableRecordImpl<R>> {

    <V> Optional<R> findOne(V value, TableField<R, V> field);
    <V> R getOne(V value, TableField<R, V> field);
    Collection<R> findAll();
    <V> Collection<R> findAll(V value, TableField<R, V> field);
    Page<R> findAll(Pageable pageable);
    <V> Page<R> findAll(V value, TableField<R, V> field, Pageable pageable);
    R update(R r);
    R insert(R r);
    <V> void delete(V value, TableField<R, V> field);
}
