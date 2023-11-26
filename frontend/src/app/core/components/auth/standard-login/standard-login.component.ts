import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { Login } from '../../../models/login';

@Component({
  selector: 'app-standard-login',
  templateUrl: './standard-login.component.html',
  styleUrls: ['./standard-login.component.scss'],
})
export class StandardLoginComponent {
  @Output() register = new EventEmitter<void>();

  readonly form = this.fb.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });
  loading = false;

  constructor(private auth: AuthService, private fb: FormBuilder, private router: Router) {}

  onLogin() {
    this.form.markAllAsTouched();
    if (this.isEmailInvalid() || this.isPasswordInvalid()) {
      return;
    }

    this.loading = true;
    this.auth.login(this.form.value as Login).subscribe({
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

  onRegister() {
    this.register.emit();
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
