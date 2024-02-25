package com.fenrir.core.feature.album.dto;

import com.fenrir.core.domain.enums.AlbumVisibility;
import lombok.Data;

@Data
public class CreateEditAlbumRequest {
    private String name;
    private String description;
    private AlbumVisibility visibility;
}
