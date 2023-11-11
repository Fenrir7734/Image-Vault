import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { HomeViewComponent } from './home-view.component';

@NgModule({
  declarations: [HomeViewComponent],
  imports: [RouterModule.forChild([{ path: '', component: HomeViewComponent, canActivate: [authGuard] }])],
})
export class HomeViewModule {}
