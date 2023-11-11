import { CurrentUser } from '../../models/current-user';

export interface CurrentUserStateModel {
  currentUser: CurrentUser | undefined;
}
