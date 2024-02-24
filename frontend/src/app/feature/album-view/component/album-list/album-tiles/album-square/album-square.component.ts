import { Component } from '@angular/core';
import { AbstractAlbumTile } from '../abstract-album-tile.directive';

@Component({
  selector: 'app-album-square',
  templateUrl: './album-square.component.html',
  styleUrls: ['./album-square.component.scss'],
})
export class AlbumSquareComponent extends AbstractAlbumTile {}
