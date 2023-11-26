import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { ButtonModule } from 'primeng/button';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

@NgModule({
  declarations: [],
  imports: [InputTextModule, TooltipModule, ButtonModule, ProgressSpinnerModule],
  exports: [InputTextModule, TooltipModule, ButtonModule, ProgressSpinnerModule],
})
export class PrimeNgModule {}
