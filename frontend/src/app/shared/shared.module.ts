import { NgModule } from '@angular/core';
import { SpinnerComponent } from './spinner/spinner.component';
import { MaterialModule } from './material.module';
import { IsAdminDirective } from './directives/is-admin.directive';
import { IsUserDirective } from './directives/is-user.directive';
import { IsEllipsisActiveDirective } from './directives/is-ellipsis-active.directive';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AddClassOnClickDirective } from './directives/add-class-on-click.directive';
import { TranslateModule } from '@ngx-translate/core';
import { CapitalizedPipe } from './pipe/capitalized.pipe';

@NgModule({
  declarations: [
    SpinnerComponent,
    IsAdminDirective,
    IsUserDirective,
    IsEllipsisActiveDirective,
    AddClassOnClickDirective,
    CapitalizedPipe,
  ],
  imports: [TranslateModule, MaterialModule, CommonModule],
  exports: [
    CommonModule,
    MaterialModule,
    HttpClientModule,
    TranslateModule,
    SpinnerComponent,
    IsAdminDirective,
    IsUserDirective,
    IsEllipsisActiveDirective,
    AddClassOnClickDirective,
    CapitalizedPipe,
  ],
})
export class SharedModule {}
