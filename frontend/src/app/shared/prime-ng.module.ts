import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { ButtonModule } from 'primeng/button';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { PaginatorModule } from 'primeng/paginator';

@NgModule({
  declarations: [],
  imports: [InputTextModule, TooltipModule, ButtonModule, ProgressSpinnerModule, PaginatorModule],
  exports: [InputTextModule, TooltipModule, ButtonModule, ProgressSpinnerModule, PaginatorModule],
})
export class PrimeNgModule {}
