import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

const PASSWORD_MIN_LENGTH = 8;
const LOWER_CASE_REGEX = /.*[a-z].*/;
const UPPER_CASE_REGEX = /.*[A-Z].*/;
const DIGIT_REGEX = /.*\d.*/;
const SPECIAL_CHARACTERS_REGEX = /.*[@#$%^&+=!].*/;

export function passwordValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    if (!control.value || !(typeof control.value === 'string')) return null;

    const password = control.value as string;
    const weakPassword =
      password.length < PASSWORD_MIN_LENGTH ||
      !LOWER_CASE_REGEX.test(password) ||
      !UPPER_CASE_REGEX.test(password) ||
      !DIGIT_REGEX.test(password) ||
      !SPECIAL_CHARACTERS_REGEX.test(password);
    return weakPassword ? { weakPassword: { value: password } } : null;
  };
}
