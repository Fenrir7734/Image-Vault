import { CurrentUserStateModel } from './current-user.model';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { Injectable } from '@angular/core';
import { CurrentUser } from '../../models/current-user';
import { CurrentUserService } from '../../services/current-user.service';
import { CurrentUserStateActions } from './current-user.actions';

@State<CurrentUserStateModel>({
  name: 'currentUser',
  defaults: {
    currentUser: undefined,
  },
})
@Injectable()
export class CurrentUserState {
  @Selector()
  static getCurrentUser(state: CurrentUserStateModel): CurrentUser | undefined {
    return state.currentUser;
  }

  constructor(private currentUserService: CurrentUserService) {}

  @Action(CurrentUserStateActions.GetCurrentUser)
  getCurrentUser(ctx: StateContext<CurrentUserStateModel>) {
    return this.currentUserService.fetchCurrentUser().subscribe(me => {
      ctx.setState({
        currentUser: me,
      });
    });
  }
}
