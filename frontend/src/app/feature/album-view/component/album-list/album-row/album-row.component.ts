import { Component, Input } from '@angular/core';
import { Album } from '../../../model/album';
import { faEllipsisVertical, faFolder, faUsers } from '@fortawesome/free-solid-svg-icons';
import { AlbumVisibility } from '../../../model/album-visibility';

@Component({
  selector: 'app-album-row',
  templateUrl: './album-row.component.html',
  styleUrls: ['./album-row.component.scss'],
})
export class AlbumRowComponent {
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
