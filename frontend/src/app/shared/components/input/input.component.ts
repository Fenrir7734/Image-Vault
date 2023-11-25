import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
})
export class InputComponent {
  @Input() label: string = '';
  @Input() placeholder: string;
  @Input() type: 'text' | 'tel' | 'email' | 'password' = 'text';
  @Input() state: 'valid' | 'warning' | 'error' = 'valid';
  @Input() required = false;
  @Input() tooltip: string;
  @Input() error: string;
  @Output() focused = new EventEmitter<void>();
}
