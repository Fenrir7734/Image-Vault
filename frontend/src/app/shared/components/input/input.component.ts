import { Component, forwardRef, Input } from '@angular/core';
import { faCircleQuestion } from '@fortawesome/free-solid-svg-icons';
import { faCircleXmark } from '@fortawesome/free-solid-svg-icons';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: forwardRef(() => InputComponent),
    },
  ],
})
export class InputComponent implements ControlValueAccessor {
  @Input() type: 'text' | 'number' | 'password' = 'text';
  @Input() value: string | number;
  @Input() label: string;
  @Input() labelType: 'float' | 'placeholder' = 'float';
  @Input() infoMsg = '';
  @Input() errorMsg = '';
  @Input() disabled = false;
  @Input() error = false;
  @Input() size: 'small' | 'medium' | 'large' | 'fill' = 'medium';

  touched = false;
  onChange: any = () => {};
  onTouched: any = () => {};

  infoIcon = faCircleQuestion;
  errorIcon = faCircleXmark;

  shouldShowInfoMsg(): boolean {
    return !!this.infoMsg && (!this.error || !this.errorMsg);
  }

  shouldShowErrorMsg(): boolean {
    return !!this.errorMsg && this.error;
  }

  changes(event: Event) {
    if (this.disabled) return;
    this.markAsTouched();
    this.value = event?.target ? (event?.target as HTMLTextAreaElement).value : '';
    this.onChange(this.value);
  }

  writeValue(value: string | number): void {
    this.value = value;
  }

  registerOnChange(onChange: any): void {
    this.onChange = onChange;
  }

  registerOnTouched(onTouched: any): void {
    this.onTouched = onTouched;
  }

  setDisabledState(disabled: boolean) {
    this.disabled = disabled;
  }

  markAsTouched() {
    if (!this.touched) {
      this.onTouched();
      this.touched = true;
    }
  }
}
