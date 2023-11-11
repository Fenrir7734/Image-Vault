package com.fenrir.core.domain.enums;

import com.fenrir.core.common.exception.exceptions.InternalServerError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum AlbumVisibility {
    PRIVATE(1L),
    PUBLIC_RO(2L),
    PUBLIC_RW(3L);

    private final Long id;

    public boolean isWriteProtected() {
        return this == PRIVATE || this == PUBLIC_RO;
    }

    public static AlbumVisibility get(Long id) {
        return Arrays.stream(AlbumVisibility.values())
                .filter(v -> v.id.equals(id))
                .findFirst()
                .orElseThrow(InternalServerError::new);
    }
}
