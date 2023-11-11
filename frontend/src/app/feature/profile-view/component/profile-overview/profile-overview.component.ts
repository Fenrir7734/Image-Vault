import { Component, Input } from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-profile-overview',
  templateUrl: './profile-overview.component.html',
  styleUrls: ['./profile-overview.component.scss'],
})
export class ProfileOverviewComponent {
  @Input() user: User;

  get username(): string {
    return this.user.name ?? this.user.externalName;
  }
}
