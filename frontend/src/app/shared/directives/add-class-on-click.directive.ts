import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appAddClassOnClick]',
})
export class AddClassOnClickDirective {
  @Input() appAddClassOnClick: string;

  constructor(private renderer: Renderer2, private elementRef: ElementRef) {}

  @HostListener('mousedown')
  onMousedown() {
    this.renderer.addClass(this.elementRef.nativeElement, this.appAddClassOnClick);
  }

  @HostListener('mouseup')
  onMouseup() {
    this.renderer.removeClass(this.elementRef.nativeElement, this.appAddClassOnClick);
  }
}
