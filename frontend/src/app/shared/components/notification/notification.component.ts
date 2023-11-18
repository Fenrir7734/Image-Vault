import { Component } from '@angular/core';
import { NotificationService } from './notification.service';
import { NotificationType } from './notification-type';
import { notificationAnimation } from './notification-animation';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss'],
  animations: [notificationAnimation],
})
export class NotificationComponent {
  constructor(public notificationService: NotificationService) {}

  getColor(type: NotificationType) {
    switch (type) {
      case NotificationType.SUCCESS:
        return 'success';
      case NotificationType.FAILURE:
        return 'failure';
      case NotificationType.INFO:
        return 'info';
      case NotificationType.WARNING:
        return 'warning';
      default:
        return 'blue';
    }
  }
}
