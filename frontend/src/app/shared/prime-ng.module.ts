import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [],
  imports: [InputTextModule, TooltipModule, ButtonModule],
  exports: [InputTextModule, TooltipModule, ButtonModule],
})
export class PrimeNgModule {}
