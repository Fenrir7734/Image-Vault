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
import { CapitalizedPipe } from './pipes/capitalized.pipe';
import { PrimeNgModule } from './prime-ng.module';
import { InputComponent } from './components/input/input.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonComponent } from './components/button/button.component';
import { ValidationErrorMessagePipe } from './pipes/validation-error-message.pipe';
import { HeaderComponent } from './components/header/header.component';
import { ScrollableComponent } from './components/scrollable/scrollable.component';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { allIcons, NgxBootstrapIconsModule } from 'ngx-bootstrap-icons';
import { IconButtonComponent } from './components/icon-button/icon-button.component';

@NgModule({
  declarations: [
    SpinnerComponent,
    IsAdminDirective,
    IsUserDirective,
    IsEllipsisActiveDirective,
    AddClassOnClickDirective,
    CapitalizedPipe,
    ValidationErrorMessagePipe,
    InputComponent,
    ButtonComponent,
    IconButtonComponent,
    HeaderComponent,
    ScrollableComponent,
    PaginatorComponent,
  ],
  imports: [
    TranslateModule,
    MaterialModule,
    PrimeNgModule,
    CommonModule,
    FontAwesomeModule,
    NgScrollbarModule,
    NgxBootstrapIconsModule.pick(allIcons),
  ],
  exports: [
    CommonModule,
    MaterialModule,
    PrimeNgModule,
    FontAwesomeModule,
    NgxBootstrapIconsModule,
    HttpClientModule,
    TranslateModule,
    SpinnerComponent,
    IsAdminDirective,
    IsUserDirective,
    IsEllipsisActiveDirective,
    AddClassOnClickDirective,
    CapitalizedPipe,
    ValidationErrorMessagePipe,
    InputComponent,
    ButtonComponent,
    IconButtonComponent,
    HeaderComponent,
    ScrollableComponent,
    PaginatorComponent,
  ],
})
export class SharedModule {}
