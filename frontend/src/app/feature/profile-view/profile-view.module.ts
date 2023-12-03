import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { ProfileViewComponent } from './component/profile-view.component';
import { SharedModule } from '../../shared/shared.module';
import { NgxsModule } from '@ngxs/store';
import { UserState } from './state/user.state';
import { ProfileAvatarComponent } from './component/profile-overview/profile-avatar/profile-avatar.component';
import { ProfileOverviewComponent } from './component/profile-overview/profile-overview.component';
import { ProfileStatusComponent } from './component/profile-overview/profile-status/profile-status.component';
import { ProfileInfoComponent } from './component/profile-info/profile-info.component';

@NgModule({
  declarations: [
    ProfileViewComponent,
    ProfileAvatarComponent,
    ProfileOverviewComponent,
    ProfileStatusComponent,
    ProfileInfoComponent,
  ],
  imports: [
    RouterModule.forChild([
      {
        path: ':id',
        component: ProfileViewComponent,
        canActivate: [authGuard],
      },
    ]),
    NgxsModule.forFeature([UserState]),
    SharedModule,
  ],
})
export class ProfileViewModule {}
