import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IconName } from 'ngx-bootstrap-icons';
import { IconDefinition } from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-icon-button',
  templateUrl: './icon-button.component.html',
  styleUrls: ['./icon-button.component.scss'],
})
export class IconButtonComponent {
  @Input() icon: IconName | IconDefinition;
  @Output() clicked = new EventEmitter<void>();

  click() {
    this.clicked.emit();
  }

  isBootstrapIcon(): boolean {
    return typeof this.icon === 'string';
  }

  isFontAwesomeIcon(): boolean {
    return !this.isBootstrapIcon();
  }

  asBootstrapIcon(): IconName {
    return <IconName>this.icon;
  }

  asFontAwesomeIcon(): IconDefinition {
    return <IconDefinition>this.icon;
  }
}
