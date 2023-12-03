import { Component, Input } from '@angular/core';
import { faEllipsisVertical, faFolder, faUsers } from '@fortawesome/free-solid-svg-icons';
import { AlbumVisibility } from '../../../model/album-visibility';
import { Album } from '../../../model/album';

@Component({
  selector: 'app-album-square',
  templateUrl: './album-square.component.html',
  styleUrls: ['./album-square.component.scss'],
})
export class AlbumSquareComponent {
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
