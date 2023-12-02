import { Component, Input } from '@angular/core';
import { faEllipsisVertical, faFolder } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-album-card',
  templateUrl: './album-card.component.html',
  styleUrls: ['./album-card.component.scss'],
})
export class AlbumCardComponent {
  @Input() id: number;
  @Input() name: string;

  albumIcon = faFolder;
  ellipsisIcon = faEllipsisVertical;

  navigateToAlbum() {}

  openContextMenu() {}
}
