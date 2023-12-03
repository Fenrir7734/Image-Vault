import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { AlbumViewComponent } from './component/album-view.component';
import { NgxsModule } from '@ngxs/store';
import { AlbumState } from './state/album.state';
import { SharedModule } from '../../shared/shared.module';
import { AlbumCardComponent } from './component/album-list/album-card/album-card.component';
import { AlbumListComponent } from './component/album-list/album-list.component';
import { AlbumSquareComponent } from './component/album-list/album-square/album-square.component';
import { AlbumRowComponent } from './component/album-list/album-row/album-row.component';

@NgModule({
  declarations: [AlbumViewComponent, AlbumCardComponent, AlbumListComponent, AlbumSquareComponent, AlbumRowComponent],
  imports: [
    RouterModule.forChild([
      {
        path: '',
        component: AlbumViewComponent,
        canActivate: [authGuard],
      },
    ]),
    NgxsModule.forFeature([AlbumState]),
    SharedModule,
  ],
})
export class AlbumViewModule {}
