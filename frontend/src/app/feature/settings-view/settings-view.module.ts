import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';
import { SettingsViewComponent } from './settings-view.component';

@NgModule({
  declarations: [SettingsViewComponent],
  imports: [RouterModule.forChild([{ path: '', component: SettingsViewComponent, canActivate: [authGuard] }])],
})
export class SettingsViewModule {}
