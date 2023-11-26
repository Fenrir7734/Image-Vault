import { Pipe, PipeTransform } from '@angular/core';
import { ValidationErrors } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

@Pipe({
  name: 'validationErrorMessage',
})
export class ValidationErrorMessagePipe implements PipeTransform {
  constructor(private translate: TranslateService) {}

  transform(errors: ValidationErrors | undefined | null): string {
    if (!errors || Object.keys(errors).length === 0) {
      return '';
    }
    const errorKey = Object.keys(errors)[0];

    switch (errorKey) {
      case 'required':
      case 'email':
      case 'weakPassword':
      case 'passwordMatch':
        return this.translate.instant(`VALIDATION.${errorKey}`);
      case 'minlength':
      case 'maxlength':
        return this.translate.instant(`VALIDATION.${errorKey}`, {
          requiredLength: this.getByKey(errorKey, errors)?.requiredLength,
        });
      default:
        return '';
    }
  }

  private getByKey(key: string, errors: ValidationErrors): any {
    type ObjectKey = keyof typeof errors;
    const k = key as ObjectKey;
    return errors[k];
  }
}
