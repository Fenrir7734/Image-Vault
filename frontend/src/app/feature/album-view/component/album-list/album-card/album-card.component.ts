import { Component, Input } from '@angular/core';
import { faEllipsisVertical, faFolder, faUsers } from '@fortawesome/free-solid-svg-icons';
import { Album } from '../../../model/album';
import { AlbumVisibility } from '../../../model/album-visibility';

@Component({
  selector: 'app-album-card',
  templateUrl: './album-card.component.html',
  styleUrls: ['./album-card.component.scss'],
})
export class AlbumCardComponent {
  @Input() album: Album;

  albumIcon = faFolder;
  ellipsisIcon = faEllipsisVertical;
  usersIcon = faUsers;

  isPublic() {
    return this.album.visibility === AlbumVisibility.PUBLIC_RO || this.album.visibility === AlbumVisibility.PUBLIC_RW;
  }

  navigateToAlbum() {}

  openContextMenu() {}
}
