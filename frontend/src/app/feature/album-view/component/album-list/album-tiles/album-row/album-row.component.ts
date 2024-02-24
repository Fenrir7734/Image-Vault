import { Component } from '@angular/core';
import { AbstractAlbumTile } from '../abstract-album-tile.directive';

@Component({
  selector: 'app-album-row',
  templateUrl: './album-row.component.html',
  styleUrls: ['./album-row.component.scss'],
})
export class AlbumRowComponent extends AbstractAlbumTile {}
