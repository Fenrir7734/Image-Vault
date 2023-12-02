import { Component, Input } from '@angular/core';
import { Album } from '../../model/album';
import { AlbumListLayout } from './album-list-layout';

@Component({
  selector: 'app-album-list',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.scss'],
})
export class AlbumListComponent {
  @Input() albums: Album[] | null;
  @Input() layout: AlbumListLayout = 'card';

  isCard() {
    return this.layout === 'card';
  }

  isSquare() {
    return this.layout === 'square';
  }
}
