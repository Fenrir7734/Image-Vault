import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
})
export class ToolbarComponent {
  @Output() private menuClicked = new EventEmitter<void>();

  onMenuClicked() {
    this.menuClicked.emit();
  }
}
