import { Component, OnInit } from '@angular/core';
import { Select, Store } from '@ngxs/store';
import { CurrentUserState } from '../../state/current-user/current-user.state';
import { Observable } from 'rxjs';
import { CurrentUser } from '../../models/current-user';
import { CurrentUserStateActions } from '../../state/current-user/current-user.actions';
import { DeviceDetectorService } from 'ngx-device-detector';
import { MatDrawerMode } from '@angular/material/sidenav';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
})
export class MainComponent implements OnInit {
  @Select(CurrentUserState.getCurrentUser)
  currentUser$: Observable<CurrentUser> | undefined;

  opened = true;

  constructor(private deviceService: DeviceDetectorService, private store: Store) {}

  ngOnInit() {
    this.opened = !this.deviceService.isMobile();

    this.store.dispatch(new CurrentUserStateActions.GetCurrentUser());
  }

  onMenuClicked() {
    this.opened = !this.opened;
  }

  get mode(): MatDrawerMode {
    return this.deviceService.isMobile() ? 'over' : 'side';
  }
}
