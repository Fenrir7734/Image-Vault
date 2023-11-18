import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { OverlayService } from '../../services/overlay.service';
import { ConfirmationConfig } from './confirmation-config';
import { ConfirmationComponent } from './confirmation.component';

@Injectable({
  providedIn: 'root',
})
export class ConfirmationService {
  private readonly _configs = new BehaviorSubject<ConfirmationConfig[]>([]);
  readonly configs$ = this._configs.asObservable();

  constructor(private overlayService: OverlayService) {}

  show(config: ConfirmationConfig) {
    this._configs.next([...this._configs.value, config]);
    this.overlayService.show(ConfirmationComponent);
  }

  hide(config: ConfirmationConfig) {
    const configs = this._configs.value;
    const idx = configs.indexOf(config);
    if (idx !== -1) {
      configs.splice(idx, 1);
      this._configs.next([...configs]);
    }
    if (configs.length == 0) {
      this.overlayService.hide();
    }
  }
}
