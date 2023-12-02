import { Component, Input } from '@angular/core';
import { Album } from '../../model/album';

@Component({
  selector: 'app-album-list',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.scss'],
})
export class AlbumListComponent {
  @Input() albums: Album[] | null;
  @Input() type: 'card' | 'square' = 'card';

  isCard() {
    return this.type === 'card';
  }

  isSquare() {
    return this.type === 'square';
  }
}
