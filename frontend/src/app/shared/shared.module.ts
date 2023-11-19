import { NgModule } from '@angular/core';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { MaterialModule } from './material.module';
import { IsAdminDirective } from './directives/is-admin.directive';
import { IsUserDirective } from './directives/is-user.directive';
import { IsEllipsisActiveDirective } from './directives/is-ellipsis-active.directive';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AddClassOnClickDirective } from './directives/add-class-on-click.directive';
import { TranslateModule } from '@ngx-translate/core';
import { CapitalizedPipe } from './pipe/capitalized.pipe';
import { NotificationComponent } from './components/notification/notification.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { ButtonComponent } from './components/button/button.component';

@NgModule({
  declarations: [
    SpinnerComponent,
    NotificationComponent,
    ConfirmationComponent,
    IsAdminDirective,
    IsUserDirective,
    IsEllipsisActiveDirective,
    AddClassOnClickDirective,
    CapitalizedPipe,
    ButtonComponent,
  ],
  imports: [TranslateModule, MaterialModule, CommonModule],
  exports: [
    CommonModule,
    MaterialModule,
    HttpClientModule,
    TranslateModule,
    SpinnerComponent,
    NotificationComponent,
    ConfirmationComponent,
    IsAdminDirective,
    IsUserDirective,
    IsEllipsisActiveDirective,
    AddClassOnClickDirective,
    CapitalizedPipe,
    ButtonComponent,
  ],
})
export class SharedModule {}
