import { Component, OnInit } from '@angular/core';
import { Select, Store } from '@ngxs/store';
import { AlbumState } from '../state/album.state';
import { Observable } from 'rxjs';
import { Album } from '../model/album';
import { AlbumStateActions } from '../state/album.actions';
import { PageRequest } from '../../../shared/models/page/page-request';
import { Pagination } from '../../../shared/models/page/page';
import { AlbumListLayout } from './album-list/album-list-layout';

@Component({
  selector: 'app-album-view',
  templateUrl: './album-view.component.html',
  styleUrls: ['./album-view.component.scss'],
})
export class AlbumViewComponent implements OnInit {
  @Select(AlbumState.getAll)
  albums$: Observable<Album[]>;
  @Select(AlbumState.getPagination)
  pagination$: Observable<Pagination>;

  layout: AlbumListLayout = 'square';

  constructor(private store: Store) {}

  ngOnInit(): void {
    if (this.store.selectSnapshot(AlbumState.getAll)?.length === 0) {
      this.store.dispatch(new AlbumStateActions.GetPage(PageRequest.first(100)));
    }
  }

  changePage(pageRequest: PageRequest) {
    this.store.dispatch(new AlbumStateActions.GetPage(pageRequest));
  }

  onlyPage(pagination: Pagination): boolean {
    return pagination.first && pagination.last;
  }

  isLayout(layout: AlbumListLayout): boolean {
    return this.layout === layout;
  }

  switchLayout(layout: AlbumListLayout) {
    this.layout = layout;
  }
}
