import { Component, EventEmitter, Output } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { FormBuilder, ValidationErrors, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Register } from '../../../models/register';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
})
export class RegistrationComponent {
  @Output() goBack = new EventEmitter<void>();

  readonly form = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
    email: ['', [Validators.required, Validators.email, Validators.minLength(2), Validators.maxLength(256)]],
    password: ['', [Validators.required]],
    passwordRepeat: ['', [Validators.required]],
  });
  loading = false;

  constructor(
    private auth: AuthService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private translate: TranslateService
  ) {}

  onRegister() {
    if (!this.isFormValid()) {
      return;
    }

    this.loading = true;
    this.auth.register(this.form.value as Register).subscribe({
      next: () => {
        this.loading = false;
        this.registerSuccessful();
        this.onGoBack();
      },
      error: () => {
        this.loading = false;
      },
    });
  }

  onGoBack() {
    this.goBack.emit();
  }

  private isFormValid(): boolean {
    this.form.markAllAsTouched();
    return this.form.valid;
  }

  private registerSuccessful() {
    this.messageService.add({
      severity: 'info',
      summary: this.translate.instant('AUTH.registered'),
      detail: this.translate.instant('AUTH.registration-successful'),
    });
  }

  isControlValid(name: string): boolean {
    const control = this.form.get(name);
    return !!control && control.touched && !control.valid;
  }

  getErrors(name: string): ValidationErrors | undefined | null {
    return this.form.get(name)?.errors;
  }
}
