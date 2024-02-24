import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { ButtonModule } from 'primeng/button';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { PaginatorModule } from 'primeng/paginator';
import { MenuModule } from 'primeng/menu';

@NgModule({
  declarations: [],
  imports: [InputTextModule, MenuModule, TooltipModule, ButtonModule, ProgressSpinnerModule, PaginatorModule],
  exports: [InputTextModule, MenuModule, TooltipModule, ButtonModule, ProgressSpinnerModule, PaginatorModule],
})
export class PrimeNgModule {}
