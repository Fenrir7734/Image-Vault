import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { GalleryViewComponent } from './gallery-view.component';

@NgModule({
  declarations: [GalleryViewComponent],
  imports: [RouterModule.forChild([{ path: '', component: GalleryViewComponent, canActivate: [authGuard] }])],
})
export class GalleryViewModule {}
