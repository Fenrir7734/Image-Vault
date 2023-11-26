import { Component } from '@angular/core';
import { faFacebook, faGoogle } from '@fortawesome/free-brands-svg-icons';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-social-login',
  templateUrl: './social-login.component.html',
  styleUrls: ['./social-login.component.scss'],
})
export class SocialLoginComponent {
  googleIcon = faGoogle;
  facebookIcon = faFacebook;

  constructor(public auth: AuthService) {}
}
