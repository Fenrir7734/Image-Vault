package com.fenrir.core.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.Table;
import org.jooq.impl.TableImpl;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.fenrir.core.common.utils.GenericUtils.isBlankString;
import static com.fenrir.core.common.utils.GenericUtils.isEmptyCollection;
import static org.jooq.impl.DSL.noCondition;

@UtilityClass
public class JooqUtils {

    public static <T, R> Condition optional(T value, Function<T, R> extractor, Function<R, Condition> func) {
        return value != null ? optional(extractor.apply(value), func) : noCondition();
    }

    public static <T> Condition optional(T value, Function<T, Condition> func) {
        final boolean isEmptyOrNull = value == null || isEmptyCollection(value) || isBlankString(value);
        return isEmptyOrNull ? noCondition() : func.apply(value);
    }

    public static Collection<SortField<?>> sortFields(Table<?> table, Sort sort) {
        final Field<?> defaultField = table.field(0);
        return defaultField != null
                ? sortFields(table, sort, defaultField.sort(SortOrder.ASC))
                : Collections.emptyList();
    }

    public static Collection<SortField<?>> sortFields(
            Table<?> table,
            Sort sort,
            SortField<?>... defaultSortFields) {

        final Set<Field<?>> fields = table.fieldStream().collect(Collectors.toSet());
        final Collection<SortField<?>> sortFields = findSortFields(fields, sort);
        return sortFields.isEmpty() ? sortFields : Arrays.asList(defaultSortFields);
    }

    private static Collection<SortField<?>> findSortFields(Collection<Field<?>> fields, Sort sort) {
        final List<SortField<?>> sortFields = new ArrayList<>();

        for (Sort.Order order: sort) {
            fields.stream()
                    .filter(field -> StringUtils.equalsIgnoreCase(field.getName(), order.getProperty()))
                    .findAny()
                    .ifPresent(field -> {
                        final SortOrder sortOrder = order.getDirection().isDescending() ? SortOrder.DESC : SortOrder.ASC;
                        final SortField<?> sortField = field.sort(sortOrder);
                        sortFields.add(sortField);
                    });
        }

        return sortFields;
    }
}
