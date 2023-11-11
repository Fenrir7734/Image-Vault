import { Action, Selector, State, StateContext } from '@ngxs/store';
import { UserStateModel } from './user.model';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { UserStateActions } from './user.actions';
import GetUser = UserStateActions.GetUser;

@State<UserStateModel>({
  name: 'user',
  defaults: {
    user: undefined,
  },
})
@Injectable()
export class UserState {
  @Selector()
  static getUser(state: UserStateModel): User | undefined {
    return state.user;
  }

  constructor(private userService: UserService) {}

  @Action(UserStateActions.GetUser)
  getUser(ctx: StateContext<UserStateModel>, payload: GetUser) {
    if (ctx.getState().user?.id === payload.id) {
      return;
    }

    return this.userService.fetchUser(payload.id).subscribe(user => {
      ctx.setState({
        user: user,
      });
    });
  }
}
