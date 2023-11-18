import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { HomeViewComponent } from './home-view.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [HomeViewComponent],
  imports: [
    RouterModule.forChild([{ path: '', component: HomeViewComponent, canActivate: [authGuard] }]),
    SharedModule,
  ],
})
export class HomeViewModule {}
