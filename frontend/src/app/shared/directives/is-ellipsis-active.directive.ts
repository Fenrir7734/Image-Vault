import { Directive, ElementRef, HostListener } from '@angular/core';
import { MatTooltip } from '@angular/material/tooltip';

@Directive({
  selector: '[appIsEllipsisActive]',
})
export class IsEllipsisActiveDirective {
  constructor(private matTooltip: MatTooltip, private elementRef: ElementRef) {}

  @HostListener('mouseenter')
  onMouseEnter(): void {
    const element = this.elementRef.nativeElement;
    this.matTooltip.disabled = element.scrollWidth <= element.clientWidth;
  }
}
