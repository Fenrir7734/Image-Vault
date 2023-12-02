import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { AlbumViewComponent } from './component/album-view.component';
import { NgxsModule } from '@ngxs/store';
import { AlbumState } from './state/album.state';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [AlbumViewComponent],
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
