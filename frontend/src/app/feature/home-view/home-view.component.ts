import { Component } from '@angular/core';
import { ConfirmationService } from '../../shared/components/confirmation/confirmation.service';

@Component({
  selector: 'app-home-view',
  templateUrl: './home-view.component.html',
  styleUrls: ['./home-view.component.scss'],
})
export class HomeViewComponent {
  constructor(public modal: ConfirmationService) {}

  onClick() {
    this.modal.show({
      header: 'Header',
      message: 'message',
      closable: true,
      onAccept: () => console.log('accept'),
      onReject: () => console.log('reject'),
    });
  }
}
