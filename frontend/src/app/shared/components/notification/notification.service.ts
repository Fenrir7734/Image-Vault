import { BehaviorSubject, take, timer } from 'rxjs';
import { Injectable } from '@angular/core';
import { Notification } from './notification';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private readonly _notifications = new BehaviorSubject<Notification[]>([]);
  readonly notifications$ = this._notifications.asObservable();

  push(notification: Notification) {
    this._notifications.next([...this._notifications.value, notification]);
    timer(10000)
      .pipe(take(1))
      .subscribe(() => {
        this.remove(notification);
      });
  }

  remove(notification: Notification) {
    const notifications = this._notifications.value;
    const idx = notifications.indexOf(notification);
    if (idx !== -1) {
      notifications.splice(idx, 1);
      this._notifications.next([...notifications]);
    }
  }
}
