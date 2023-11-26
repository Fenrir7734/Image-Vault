import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-standard-login',
  templateUrl: './standard-login.component.html',
  styleUrls: ['./standard-login.component.scss'],
})
export class StandardLoginComponent {
  loading = false;

  readonly form = this.fb.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  constructor(public auth: AuthService, private fb: FormBuilder, private router: Router) {}

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
      next: () => {
        this.router.navigate(['home']);
      },
      error: () => {
        this.loading = false;
      },
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