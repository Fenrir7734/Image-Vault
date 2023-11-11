package com.fenrir.core.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

@UtilityClass
public class GenericUtils {

    public static <T> boolean isEmptyCollection(T value) {
        if (value instanceof Collection<?>) {
            final Collection<?> collection = (Collection<?>) value;
            return collection.isEmpty();
        }
        return false;
    }

    public static <T> boolean isBlankString(T value) {
        if (value instanceof String) {
            final String str = (String) value;
            return StringUtils.stripToNull(str) == null;
        }
        return false;
    }
}
