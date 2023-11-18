import { NotificationType } from './notification-type';

export interface Notification {
  header: string;
  message: string;
  type: NotificationType;
}
