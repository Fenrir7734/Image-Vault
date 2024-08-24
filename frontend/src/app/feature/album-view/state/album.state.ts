import { Action, Selector, State, StateContext } from '@ngxs/store';
import { AlbumStateModel } from './album.model';
import { Injectable } from '@angular/core';
import { Album } from '../model/album';
import { AlbumService } from '../service/album.service';
import { AlbumStateActions } from './album.actions';
import GetPage = AlbumStateActions.GetPage;
import { Pagination } from '../../../shared/models/page/page';
import { PageRequest } from '../../../shared/models/page/page-request';
import Edit = AlbumStateActions.Edit;
import Create = AlbumStateActions.Create;

@State<AlbumStateModel>({
  name: 'album',
  defaults: {
    albums: [],
    pagination: undefined,
  },
})
@Injectable()
export class AlbumState {
  @Selector()
  static getAll(state: AlbumStateModel): Album[] {
    return state.albums;
  }

  @Selector()
  static getPagination(state: AlbumStateModel): Pagination | undefined {
    return state.pagination;
  }

  constructor(private albumService: AlbumService) {}

  @Action(AlbumStateActions.GetPage)
  getAll(ctx: StateContext<AlbumStateModel>, payload: GetPage) {
    return this.albumService.fetchAlbums(payload.pageRequest).subscribe(page => {
      ctx.setState({
        albums: page.content,
        pagination: page,
      });
    });
  }

  @Action(AlbumStateActions.Create)
  create(ctx: StateContext<AlbumStateModel>, payload: Create) {
    return this.albumService.createAlbum(payload.album).subscribe(() => {
      ctx.dispatch(new GetPage(this.getPageRequest(ctx)));
    });
  }

  @Action(AlbumStateActions.Edit)
  edit(ctx: StateContext<AlbumStateModel>, payload: Edit) {
    return this.albumService.editAlbum(payload.album).subscribe(() => {
      ctx.dispatch(new GetPage(this.getPageRequest(ctx)));
    });
  }

  private getPageRequest(ctx: StateContext<AlbumStateModel>): PageRequest {
    const pagination = ctx.getState().pagination;
    return pagination ? PageRequest.fromPagination(pagination) : PageRequest.default();
  }
}
