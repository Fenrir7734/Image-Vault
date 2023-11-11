import { Component, Input } from '@angular/core';
import { CurrentUser } from '../../models/current-user';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss'],
})
export class SidenavComponent {
  @Input() user: CurrentUser | null;
}
