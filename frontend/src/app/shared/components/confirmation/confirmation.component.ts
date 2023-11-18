import { Component } from '@angular/core';
import { ConfirmationConfig } from './confirmation-config';
import { ConfirmationService } from './confirmation.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss'],
})
export class ConfirmationComponent {
  constructor(public confirmationService: ConfirmationService) {}

  onAccept(config: ConfirmationConfig) {
    if (config.onAccept) {
      config.onAccept();
    }
    this.confirmationService.hide(config);
  }

  onReject(config: ConfirmationConfig) {
    if (config.onReject) {
      config.onReject();
    }
    this.confirmationService.hide(config);
  }

  onClose(config: ConfirmationConfig) {
    this.confirmationService.hide(config);
  }
}
