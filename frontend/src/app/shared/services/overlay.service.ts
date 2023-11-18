import { Injectable } from '@angular/core';
import { ComponentType, Overlay, OverlayRef } from '@angular/cdk/overlay';
import { ComponentPortal } from '@angular/cdk/portal';

@Injectable({
  providedIn: 'root',
})
export class OverlayService {
  private overlayRef: OverlayRef;

  constructor(private overlay: Overlay) {}

  show(component: ComponentType<unknown>) {
    this.overlayRef = this.overlay.create({
      hasBackdrop: true,
      positionStrategy: this.overlay.position().global().centerVertically().centerVertically(),
      backdropClass: 'overlay',
    });

    const portal = new ComponentPortal(component);
    this.overlayRef.attach(portal);
  }

  hide() {
    if (this.overlayRef) {
      this.overlayRef.detach();
    }
  }
}
