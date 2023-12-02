import { Action, Selector, State, StateContext } from '@ngxs/store';
import { AlbumStateModel } from './album.model';
import { Injectable } from '@angular/core';
import { Album } from '../model/album';
import { AlbumService } from '../service/album.service';
import { AlbumStateActions } from './album.actions';
import GetPage = AlbumStateActions.GetPage;

@State<AlbumStateModel>({
  name: 'album',
  defaults: {
    albums: [],
  },
})
@Injectable()
export class AlbumState {
  @Selector()
  static getAll(state: AlbumStateModel): Album[] {
    return state.albums;
  }

  constructor(private albumService: AlbumService) {}

  @Action(AlbumStateActions.GetPage)
  getAll(ctx: StateContext<AlbumStateModel>, payload: GetPage) {
    return this.albumService.fetchAlbums(payload.pageRequest).subscribe(page => {
      ctx.setState({
        albums: page.content,
      });
    });
  }
}
