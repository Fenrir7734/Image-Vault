package com.fenrir.core.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AlbumVisibility {
    PRIVATE,
    PUBLIC_RO,
    PUBLIC_RW;

    public boolean isWriteProtected() {
        return this == PRIVATE || this == PUBLIC_RO;
    }
}
