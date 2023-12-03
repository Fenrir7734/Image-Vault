import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function passwordMatchValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const password = control.get('password');
    const passwordRepeat = control.get('passwordRepeat');

    if (!password || !passwordRepeat || !password.touched || !passwordRepeat.touched) {
      return null;
    }

    if (password.value !== passwordRepeat.value) {
      passwordRepeat.setErrors({ passwordMatch: true });
    } else {
      delete passwordRepeat.errors?.['passwordMatch'];
    }

    return null;
  };
}
