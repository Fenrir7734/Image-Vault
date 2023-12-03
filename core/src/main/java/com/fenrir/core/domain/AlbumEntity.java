package com.fenrir.core.domain;

import com.fenrir.core.domain.enums.AlbumVisibility;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class AlbumEntity {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private String owner;
    private AlbumVisibility visibility;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
