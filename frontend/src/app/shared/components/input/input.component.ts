import { Component, Input } from '@angular/core';
import { faCircleQuestion } from '@fortawesome/free-solid-svg-icons';
import { faCircleXmark } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
})
export class InputComponent {
  @Input() type: 'text' | 'number' | 'password' = 'text';
  @Input() label: string;
  @Input() infoMsg = '';
  @Input() errorMsg = '';
  @Input() disabled = false;
  @Input() error = false;
  @Input() size: 'small' | 'medium' | 'large' | 'fill' = 'medium';

  infoIcon = faCircleQuestion;
  errorIcon = faCircleXmark;

  shouldShowInfoMsg(): boolean {
    return !!this.infoMsg && (!this.error || !this.errorMsg);
  }

  shouldShowErrorMsg(): boolean {
    return !!this.errorMsg && this.error;
  }
}
