import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IconDefinition } from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss'],
})
export class ButtonComponent {
  @Input() label: string;
  @Input() disabled = false;
  @Input() loading = false;
  @Input() look: 'full' | 'hollow' | 'inverted' = 'full';
  @Input() size: 'small' | 'medium' | 'large' | 'fill' = 'medium';
  @Input() icon: IconDefinition;
  @Output() clicked = new EventEmitter<void>();

  click() {
    this.clicked.emit();
  }
}
