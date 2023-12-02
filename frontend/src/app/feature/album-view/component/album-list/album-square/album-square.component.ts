import { Component, Input } from '@angular/core';
import { faEllipsisVertical, faFolder } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-album-square',
  templateUrl: './album-square.component.html',
  styleUrls: ['./album-square.component.scss'],
})
export class AlbumSquareComponent {
  @Input() id: number;
  @Input() name: string;

  albumIcon = faFolder;
  ellipsisIcon = faEllipsisVertical;

  navigateToAlbum() {}

  openContextMenu() {}
}
