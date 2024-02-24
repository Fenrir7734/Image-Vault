import { Component } from '@angular/core';
import { AbstractAlbumTile } from '../abstract-album-tile.directive';

@Component({
  selector: 'app-album-card',
  templateUrl: './album-card.component.html',
  styleUrls: ['./album-card.component.scss'],
})
export class AlbumCardComponent extends AbstractAlbumTile {}
