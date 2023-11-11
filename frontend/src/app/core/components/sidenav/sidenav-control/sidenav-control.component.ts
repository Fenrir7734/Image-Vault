import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-sidenav-control',
  templateUrl: './sidenav-control.component.html',
  styleUrls: ['./sidenav-control.component.scss'],
})
export class SidenavControlComponent {
  constructor(private auth: AuthService) {}

  logout() {
    this.auth.logout();
  }
}
