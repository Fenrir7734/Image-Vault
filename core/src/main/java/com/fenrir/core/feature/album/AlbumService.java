package com.fenrir.core.feature.album;

import com.fenrir.core.common.exception.exceptions.NotFoundException;
import com.fenrir.core.common.security.AuthenticationFacade;
import com.fenrir.core.database.tables.AlbumVisibility;
import com.fenrir.core.database.tables.records.AlbumVisibilityRecord;
import com.fenrir.core.database.tables.records.AlbumsRecord;
import com.fenrir.core.domain.AlbumEntity;
import com.fenrir.core.feature.album.dto.CreateEditAlbumRequest;
import com.fenrir.core.repository.AlbumRepository;
import com.fenrir.core.repository.AlbumVisibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumVisibilityRepository albumVisibilityRepository;
    private final AuthenticationFacade authenticationFacade;

    public Page<AlbumEntity> getAll(Pageable pageable) {
        return albumRepository.findAll(authenticationFacade.getUserId(), pageable);
    }

    public void createAlbum(CreateEditAlbumRequest album, Pageable pageable) {
        Long visibilityId = albumVisibilityRepository
                .findOne(album.getVisibility().name(), AlbumVisibility.ALBUM_VISIBILITY.NAME)
                .map(AlbumVisibilityRecord::getId)
                .orElseThrow(NotFoundException::new);
        AlbumsRecord albumsRecord = new AlbumsRecord();
        albumsRecord.setName(albumsRecord.getName());
        albumsRecord.setDescription(albumsRecord.getDescription());
        albumsRecord.setVisibilityId(visibilityId);
        albumsRecord.setOwnerId(authenticationFacade.getUserId());
        albumRepository.insert(albumsRecord);
    }
}
