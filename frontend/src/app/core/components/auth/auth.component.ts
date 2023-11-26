import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';
import { faFacebook } from '@fortawesome/free-brands-svg-icons';
import { FormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent {
  loading = false;
  googleIcon = faGoogle;
  facebookIcon = faFacebook;

  readonly form = this.fb.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  constructor(public auth: AuthService, private fb: FormBuilder, public messageService: MessageService) {}

  login() {
    const emailControl = this.form.get('email');
    const passwordControl = this.form.get('password');

    emailControl?.markAsTouched();
    passwordControl?.markAllAsTouched();

    if (this.isEmailInvalid() || this.isPasswordInvalid()) {
      return;
    }

    this.loading = true;
    this.auth.login(emailControl?.value ?? '', passwordControl?.value ?? '').subscribe({
      complete: () => {
        this.loading = false;
      },
    });
  }

  isEmailInvalid(): boolean {
    const control = this.form.get('email');
    return !!control && control.touched && !control.valid;
  }

  isPasswordInvalid(): boolean {
    const control = this.form.get('password');
    return !!control && control.touched && !control.valid;
  }
}
