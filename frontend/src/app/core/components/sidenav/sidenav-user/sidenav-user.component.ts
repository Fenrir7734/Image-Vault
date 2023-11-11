import { Component, Input } from '@angular/core';
import { CurrentUser } from '../../../models/current-user';
import { isNil } from 'lodash';

@Component({
  selector: 'app-sidenav-user',
  templateUrl: './sidenav-user.component.html',
  styleUrls: ['./sidenav-user.component.scss'],
})
export class SidenavUserComponent {
  @Input() user: CurrentUser | null;

  get username(): string {
    if (isNil(this.user)) {
      return '';
    }

    return this.user.name ? this.user.name : this.user.externalName;
  }
}
