package com.fenrir.core.feature.album;

import com.fenrir.core.domain.AlbumEntity;
import com.fenrir.core.feature.album.dto.CreateAlbumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    ResponseEntity<Page<AlbumEntity>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(albumService.getAll(pageable));
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateAlbumRequest album) {
        return ResponseEntity.ok().build();
    }
}
