import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { AlbumViewComponent } from './component/album-view.component';
import { NgxsModule } from '@ngxs/store';
import { AlbumState } from './state/album.state';
import { SharedModule } from '../../shared/shared.module';
import { AlbumCardComponent } from './component/album-list/album-card/album-card.component';
import { AlbumListComponent } from './component/album-list/album-list.component';

@NgModule({
  declarations: [AlbumViewComponent, AlbumCardComponent, AlbumListComponent],
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
