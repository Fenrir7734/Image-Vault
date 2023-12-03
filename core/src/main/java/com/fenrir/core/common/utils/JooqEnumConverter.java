package com.fenrir.core.common.utils;

import com.fenrir.core.domain.enums.AlbumVisibility;
import com.fenrir.core.domain.enums.AuthType;
import com.fenrir.core.domain.enums.Role;
import lombok.experimental.UtilityClass;
import org.jooq.impl.EnumConverter;

@UtilityClass
public class JooqEnumConverter {

    public static EnumConverter<String, Role> roleConverter() {
        return new EnumConverter<>(String.class, Role.class, Role::getName);
    }

    public static EnumConverter<String, AuthType> authTypeConverter() {
        return new EnumConverter<>(String.class, AuthType.class, AuthType::toString);
    }

    public static EnumConverter<String, AlbumVisibility> albumVisibilityConverter() {
        return new EnumConverter<>(String.class, AlbumVisibility.class, AlbumVisibility::toString);
    }
}
