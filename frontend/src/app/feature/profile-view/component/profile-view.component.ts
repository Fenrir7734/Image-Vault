import { Component, OnDestroy, OnInit } from '@angular/core';
import { Select, Store } from '@ngxs/store';
import { UserState } from '../state/user.state';
import { AuthService } from '../../../core/services/auth.service';
import { firstValueFrom, Observable, Subject } from 'rxjs';
import { User } from '../model/user';
import { ActivatedRoute } from '@angular/router';
import { isNumber } from 'lodash';
import { UserStateActions } from '../state/user.actions';

@Component({
  selector: 'app-profile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.scss'],
})
export class ProfileViewComponent implements OnInit, OnDestroy {
  @Select(UserState.getUser)
  user$: Observable<User> | undefined;

  private destroy$ = new Subject<void>();

  constructor(private auth: AuthService, private store: Store, private route: ActivatedRoute) {}

  async ngOnInit() {
    const userId = await this.getUserId();

    if (userId) {
      this.store.dispatch(new UserStateActions.GetUser(userId));
    }
  }

  private async getUserId(): Promise<number | undefined> {
    const params = await firstValueFrom(this.route.params);
    return isNumber(params['id']) ? params['id'] : this.auth.userId;
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
