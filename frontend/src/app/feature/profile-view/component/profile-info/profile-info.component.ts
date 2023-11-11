import { Component, Input } from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.scss'],
})
export class ProfileInfoComponent {
  @Input() user: User;
}
