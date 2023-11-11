import { NgModule } from '@angular/core';
import { NgxsModule } from '@ngxs/store';
import { CurrentUserState } from './current-user/current-user.state';

@NgModule({
  declarations: [],
  imports: [
    NgxsModule.forRoot([CurrentUserState], {
      compatibility: {
        strictContentSecurityPolicy: true,
      },
    }),
  ],
})
export class CoreStateModule {}
