import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { AlbumViewComponent } from './component/album-view.component';

@NgModule({
  declarations: [AlbumViewComponent],
  imports: [RouterModule.forChild([{ path: '', component: AlbumViewComponent, canActivate: [authGuard] }])],
})
export class AlbumViewModule {}
