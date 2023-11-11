import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-profile-status',
  templateUrl: './profile-status.component.html',
  styleUrls: ['./profile-status.component.scss'],
})
export class ProfileStatusComponent {
  @Input() enabled: boolean;
  @Input() verified: boolean;

  get isDisabled(): boolean {
    return !this.enabled;
  }

  get isUnverified(): boolean {
    return this.enabled && !this.verified;
  }

  get isActive(): boolean {
    return this.enabled && this.verified;
  }
}
